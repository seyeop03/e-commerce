package domain;

import lombok.Getter;

@Getter
public class CartItem {
    private Long cartItemId; //PK
    private Long totalPrice; //해당 상품 가격 * 수량
    private int count; //수량
    private Long cartId; //FK
    private Long itemId; //FK

    public CartItem(Long totalPrice, int count, Long cartId, Long itemId) {
        this.totalPrice = totalPrice;
        this.count = count;
        this.cartId = cartId;
        this.itemId = itemId;
    }
}
