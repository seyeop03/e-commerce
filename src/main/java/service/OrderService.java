package service;

import common.*;
import domain.Item;
import domain.Member;
import domain.Order;
import domain.OrderItem;
import repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static common.UserInput.*;

public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    //== 주문 서비스 핸들러 ==//
    public void handleOrderService(Scanner sc) {
        Member currentMember = Session.getInstance().getCurrentMember();
        CartSession cartSession = CartSession.getInstance();
        while (true) {
            displayOrderMenu();
            int choice = inputInt("선택: ", sc);
            switch (choice) {
                case 1:
                    if (isAdmin(currentMember)) {
                        allOrderSelect();
                    }else {
                        createOrder(cartSession, currentMember);
                    }

                case 2:
                    if (isAdmin(currentMember)){
                        changeOrderStatus(sc);
                    }else {
                        findOrders(sc);
                    }

                case 3:
                    if (!isAdmin(currentMember)){
                        deleteOrder(sc, currentMember);
                    }
            }
        }
    }

    private void deleteOrder(Scanner sc, Member currentMember) {
        Long id = inputLong("취소할 주문 번호: ", sc);
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            System.out.println("그런 주문은 없습니다.");
        } else {

            if (order.getMemberId().equals(currentMember.getMemberId()) && checkOrderStatus(order)) {
                orderItemRepository.deleteByOrderId(id);
                orderRepository.deleteById(id);
            } else if (order.getMemberId().equals(currentMember.getMemberId()) && !checkOrderStatus(order)){
                System.out.println("주문 취소 가능 상태가 아닙니다.");
            } else {
                System.out.println(currentMember.getName() + "님의 주문이 아닙니다.");
            }
        }
    }

    private static boolean checkOrderStatus(Order order) {
        return order.getStatus().equals(OrderStatus.valueOf("PAYMENT_WAITING"))
                || order.getStatus().equals(OrderStatus.valueOf("PAYMENT_COMPLETED"))
                || order.getStatus().equals(OrderStatus.valueOf("PREPARING"));
    }

    private void createOrder(CartSession cartSession, Member currentMember) {

        Long memberId = currentMember.getMemberId();
        String date = LocalDate.now().toString();
        Order order = Order.of(date, OrderStatus.PAYMENT_WAITING, memberId);
        Long orderId = orderRepository.save(order);
        System.out.println(orderId);
        System.out.println("ok");

        for (Long itemId : cartSession.getCart().keySet()) {
            int itemPrice = itemRepository.findItemPriceById(itemId);
            int price = (int) (itemPrice * cartSession.getCart().get(itemId));
            OrderItem orderItem = OrderItem.of(Math.toIntExact(cartSession.getCart().get(itemId)), price, orderId, itemId);
            orderItemRepository.save(orderItem);
        }


        order.setTotalPrice(orderItemRepository.findPriceByOrderId(orderId));

        System.out.println("주문이 완료되었습니다.");
    }

    private static boolean isAdmin(Member currentMember) {
        return currentMember.getRole().equals(Role.ADMIN);
    }

    private void findOrders(Scanner sc) {

        Long memberId = Session.getInstance()
                .getCurrentMember()
                .getMemberId();
        List<Order> orderList = orderRepository.findByMemberId(memberId);
        System.out.println(orderList);

        Long id = inputLong("상세 조회할 주문 번호", sc);
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);

        if (orderItems.isEmpty()){
            System.out.println("주문 번호를 확인해주세요.");
        }else {
            List<String> itemNames = orderItems.stream()
                    .map(o -> extractItemName(o.getItemId()))
                    .collect(Collectors.toList());
            System.out.println(orderItems);
            System.out.println(itemNames);
        }
    }

    private String extractItemName(Long itemId){
        Item item = itemRepository.findByItemId(itemId).orElse(null);
        return item.getName();
    }

    private void changeOrderStatus(Scanner sc) {
        Long id = inputLong("변경할 주문 번호: ", sc);
        String status = inputString("변경할 상태: ", sc);
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        orderRepository.updateById(id, orderStatus);
    }

    private void allOrderSelect() {
        List<Order> orderList = orderRepository.findAll();
        System.out.println(orderList);
    }

    private void displayOrderMenu() {
        Member currentMember = Session.getInstance().getCurrentMember();
        if (currentMember != null) {
            if (isAdmin(currentMember)) {
                System.out.println("""
                        ================================
                                   메뉴 선택 화면
                        ================================
                        1. 전체 주문 조회
                        2. 주문 상태 변경
                        0. 뒤로 가기
                        ================================
                        """);
            } else {
                System.out.println("""
                        ================================
                                   메뉴 선택 화면
                        ================================
                        1. 장바구니에 담긴 상품 주문하기
                        2. 본인 주문 조회
                        3. 주문 취소           
                        0. 뒤로 가기           
                        ================================
                        """);
            }
        } else {
            System.out.println("로그인 안됨");
        }
    }

}
