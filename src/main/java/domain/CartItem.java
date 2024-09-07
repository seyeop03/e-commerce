package domain;

import lombok.Getter;

@Getter
public class CartItem {
    private Long cartItemId; //PK
    private Long totalPrice; //장바구니 총 가격
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
