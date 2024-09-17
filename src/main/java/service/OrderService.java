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
                        viewAllOrders();
                    } else {
                        if (cartSession.getCart().isEmpty()) {
                            System.out.println("장바구니가 비어있습니다.");
                        } else {
                            createOrder(cartSession, currentMember);
                        }
                    }
                    break;
                case 2:
                    if (isAdmin(currentMember)){
                        changeOrderStatus(sc);
                    } else {
                        findOrders(sc);
                    }
                    break;
                case 3:
                    if (!isAdmin(currentMember)) deleteOrder(sc, currentMember);
                    break;
                case 0:
                    return;
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

    private void findOrders(Scanner sc) {
        /**
         * 본인의 주문 목록 조회
         */
        Long memberId = Session.getInstance()
                .getCurrentMember()
                .getMemberId();
        List<Order> orders = orderRepository.findByMemberId(memberId);
        if (orders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        } else {
            orders.forEach(o -> {
                System.out.println("주문 ID: " + o.getOrderId()
                        + " | 주문날짜: " + o.getDate()
                        + " | 총 주문 금액: " + o.getTotalPrice()
                        + " | 주문상태: " + o.getStatus()
                );}
            );
        }

        //todo: viewOrderDetails, createItemReview 메서드 추출

        /**
         * 본인 주문 상세 조회
         */
        Long id = inputLong("상세 조회할 주문 번호 선택 : ", sc);
        if (!validateOrderId(orders, id)){
            System.out.println("없는 주문이거나 본인의 주문이 아닙니다.");
            return;
        }

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
        orderItems.stream()
                .forEach(oi -> {
                    System.out.println("상품 ID: " + oi.getItemId()
                            + " | 상품명: " + extractItemName(oi.getItemId())
                            + " | 수량: " + oi.getQuantity()
                            + " | 금액: " + oi.getPrice());
                });

        orderItemRepository.findByOrderId(id)
                .stream()
                .filter(oi -> oi.getItemId().equals(id))
                .map(oi -> "상품 ID: " + oi.getItemId()
                        + " | 상품명: " + extractItemName(oi.getItemId())
                        + " | 수량: " + oi.getQuantity()
                        + " | 금액: " + oi.getPrice())
                .findAny()
                .ifPresent(System.out::println);

        /**
         * 특정 주문의 상품에 대한 리뷰 작성
         */
        Long itemId = inputLong("리뷰 작성할 상품 번호 선택 : ", sc);

        orderItems.stream()
                .filter(oi -> itemId.equals(oi.getItemId()))
                .map(oi -> createReview(memberId, itemId, sc))
                .findAny()
                .ifPresent(reviewRepository::save);

//        for (int i = 0; i < orderItems.size(); i++) {
//            if (itemId.equals(orderItems.get(i).getItemId())) {
//                int stars = inputInt("평점을 입력해주세요: ", sc);
//                String contents = inputString("내용을 입력해주세요: ", sc);
//                Review review = Review.of(stars, contents, memberId, itemId);
//                reviewRepository.save(review);
//            }
//        }
    }

    //주문 ID 검증
    private static boolean validateOrderId(List<Order> orders, Long id) {
        return orders.stream()
                .anyMatch(o -> o.getOrderId().equals(id));
    }

    private String extractItemName(Long itemId){
        Item item = itemRepository.findById(itemId).orElse(null);
        return item.getName();
    }

    private Review createReview(Long memberId, Long itemId, Scanner sc) {
        int stars = inputInt("평점을 입력해주세요: ", sc);
        String contents = inputLine("내용을 입력해주세요: ", sc);
        return Review.of(stars, contents, memberId, itemId);
    }

    //== 주문 취소 ==//
    private void deleteOrder(Scanner sc, Member currentMember) {
        Long id = inputLong("취소할 주문 번호: ", sc);
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            System.out.println("없는 주문 번호 입니다.");
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

    private static boolean isAdmin(Member currentMember) {
        return currentMember.getRole().equals(Role.ADMIN);
    }

    //== 주문 상태 변경 ==//
    private void changeOrderStatus(Scanner sc) {
        Long id = inputLong("변경할 주문 번호: ", sc);
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            System.out.println("주문 번호를 다시 확인해주세요.");
            return;
        }

        OrderStatus orderStatus = null;
        while (orderStatus == null) {
            try {
                int statusNum = inputInt("변경할 상태("
                        + Stream.of(OrderStatus.values())
                        .map(os -> os.name() + ":" + os.getStatusNum())
                        .collect(Collectors.joining(", ")) + "): ", sc);
                orderStatus = OrderStatus.fromStatusNum(statusNum);
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }

        orderRepository.updateById(id, orderStatus);

        System.out.println("주문 번호 " + id + "의 상태가 " + orderStatus + "로 변경되었습니다.");
    }

    private void viewAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        orderList.forEach(o -> {
            System.out.println("주문 ID: " + o.getOrderId()
                    + " | 주문날짜: " + o.getDate()
                    + " | 총 주문 금액: " + o.getTotalPrice()
                    + " | 주문상태: " + o.getStatus()
            );}
        );
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
        }
    }

}
