package service;

import common.*;
import domain.*;
import repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.UserInput.*;

public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ReviewRepository reviewRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository, ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.reviewRepository = reviewRepository;
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
                    break;

                case 2:
                    if (isAdmin(currentMember)){
                        changeOrderStatus(sc);
                    }else {
                        findOrders(sc);
                    }
                    break;

                case 3:
                    if (!isAdmin(currentMember)){
                        deleteOrder(sc, currentMember);
                    }
                    break;
                case 0:
                    return;
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
                System.out.println("주문 취소가 완료되었습니다.");
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

        for (Long itemId : cartSession.getCart().keySet()) {
            int itemPrice = itemRepository.findItemPriceById(itemId);
            int price = (int) (itemPrice * cartSession.getCart().get(itemId));
            OrderItem orderItem = OrderItem.of(Math.toIntExact(cartSession.getCart().get(itemId)), price, orderId, itemId);
            orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(orderItemRepository.findPriceByOrderId(orderId));
        int totalPrice = order.getTotalPrice();
        orderRepository.updateTotalPriceById(totalPrice,orderId);

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
        if (orderList.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        } else {
            for (Order order : orderList){
                System.out.println(order);
            }
        }

        Long id = inputLong("상세 조회할 주문 번호 : ", sc);
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);

        if (orderItems.isEmpty()){
            System.out.println("주문 번호를 확인해주세요.");
        }else {
            List<String> itemNames = orderItems.stream()
                    .map(o -> extractItemName(o.getItemId()))
                    .collect(Collectors.toList());
            for (OrderItem orderItem : orderItems) {
                System.out.println(orderItem);
            }
            System.out.println(itemNames);

//            Long itemId = inputLong("리뷰 작성할 상품 번호 선택 : ",sc);
//
//            for (int i = 0; i < orderItems.size(); i++) {
//                if (itemId.equals(orderItems.get(i).getItemId())) {
//                    int stars = inputInt("평점을 입력해주세요: ", sc);
//                    String contents = inputString("내용을 입력해주세요: ", sc);
//                    Review review = Review.of(stars,contents,memberId,itemId);
//                    reviewRepository.save(review);
//                }
//            } // 생각나는대로 작성해서 조금 더 생각해보겠슴다
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
        System.out.println("변경 완료");
    }

    private void allOrderSelect() {
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            System.out.println(order);
        }
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
