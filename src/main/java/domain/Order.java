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
    private int totalPrice; //총 주문 금액
    private OrderStatus status; //주문 상태
    private Long memberId; //FK

    //== 정적 팩토리 메서드 ==//
    public static Order of(String date, OrderStatus status, Long memberId) {
        return new Order(null, date, 0, status, memberId);
    }

}
