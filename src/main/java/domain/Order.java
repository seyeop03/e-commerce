package domain;

import common.OrderStatus;
import lombok.Getter;

@Getter
public class Order {
    private Long orderId; //PK
    private String date; //주문 날짜
    private int totalPrice; //총 주문 가격
    private OrderStatus status; //주문 상태
    private Long memberId; //FK

    public Order(String date, int totalPrice, OrderStatus status, Long memberId) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = status;
        this.memberId = memberId;
    }
}
