package service;

import common.Role;
import common.Session;
import common.UserInput;
import domain.Item;
import domain.Member;
import domain.Order;
import domain.OrderItem;
import repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
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
        // 상품 조회 -> 장바구니 -> 주문 생성
        // 본인 주문 조회
        // 주문 취소

        // 전체 주문 조회
        // 주문 상태 변경
        while (true){
            Member currentMember = Session.getInstance().getCurrentMember();
            displayOrderMenu();
            int choice = inputInt("선택: ", sc);
            switch (choice) {
                case 1:
                    if (isAdmin(currentMember)) {
                        allOrderSelect();
                    }else {
                        itemSelect();
                    }

                case 2:
                    if (isAdmin(currentMember)){
                        changeOrderStatus(sc);
                    }else {
                        findOrders(sc);
                    }

                case 3:
                    if (!isAdmin(currentMember)){
                        Long id = inputLong("취소할 주문 번호: ", sc);
                        Order order = orderRepository.findById(id).orElse(null);
                        if (order == null) {
                            System.out.println("그런 주문은 없습니다.");
                        } else {
                          if (Objects.equals(order.getMemberId(), currentMember.getMemberId())) {
                              orderRepository.deleteById(id);
                          } else {
                              System.out.println("님이 주문한게 아닙니다.");
                          }
                        }
                    }
            }
        }
    }

    private static boolean isAdmin(Member currentMember) {
        return currentMember.getRole().equals(Role.ADMIN);
    }

    private void findOrders(Scanner sc) {

        Long memberId = Session.getInstance()
                .getCurrentMember()
                .getMemberId();
        orderRepository.findByMemberId(memberId);

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
        Item item = itemRepository.findById(itemId).orElse(null);
        return item.getName();
    }

    private void changeOrderStatus(Scanner sc) {
        Long id = inputLong("변경할 주문 번호: ", sc);
        String status = inputString("변경할 상태: ", sc);
        //orderRepository.updateById(id, status);
    }

    private void itemSelect() {
        itemRepository.findAll();
    }

    private void allOrderSelect() {
        orderRepository.findAll();
        System.out.println("상세 조회할 주문 번호");
    }

    private void displayOrderMenu() {
        Member currentMember = Session.getInstance().getCurrentMember();
        if (currentMember != null) {
            if (isAdmin(currentMember)) {
                System.out.println("""
                        1.전체 주문 조회
                        2.주문 상태 변경
                        """);

            } else {
                System.out.println("""
                        1. 상품 검색 후 주문
                        2. 본인 주문 조회
                        3. 주문 취소
                        """);
            }
        } else {
            System.out.println("로그인 안됨");
        }
    }

}
