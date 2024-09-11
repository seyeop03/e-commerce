package domain;

import common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Order {
    @Setter
    private Long orderId; //PK
    private String date; //주문 날짜
    @Setter
//    private int totalPrice; //총 주문 가격
    private OrderStatus status; //주문 상태
    private Long memberId; //FK

    public static Order of(String date,  OrderStatus status, Long memberId) {
        return new Order(null, date, status, memberId);
    }

//    public static Order of(String date, OrderStatus status, Long memberId) {
//        return new Order(null,date, status, memberId);
//    }
}
