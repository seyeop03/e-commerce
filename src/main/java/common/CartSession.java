package common;

import java.util.HashMap;
import java.util.Map;

public class CartSession {

    private static CartSession instance;
    private static Map<Long, Long> cart = new HashMap<>(); //<itemId, 수량>

    public void addItem(Long itemId) {
        if (cart.containsKey(itemId)) {
            cart.put(itemId, cart.get(itemId)+1);
        } else {
            cart.put(itemId, 1L);
        }
    }

    public static CartSession getInstance() {
        if (instance == null) {
            return new CartSession();
        }
        return instance;
    }

    public Map<Long, Long> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}
