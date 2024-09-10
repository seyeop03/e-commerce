package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrderItem {
    private Long orderItemId; //PK
    private int quantity; //1개의 상품 수량
    private int price; //상품 * 수량
    private Long orderId; //FK
    private Long itemId; //FK

    //== 정적 팩토리 메서드 ==//
    public static OrderItem of(int quantity, int price, Long orderId, Long itemId) {
        return new OrderItem(null, quantity, price, orderId, itemId);
    }
}
