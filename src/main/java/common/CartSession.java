package common;

import domain.CartItem;
import domain.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//장바구니 구현: 상품_ID 선택 -> 카트 담기
//구현 1.
//- 로그인 시 카트 정보 끌고옴 => 카트가 존재하지 않으면(DB 조회) 생성(cartId 생성)
//- 상품 추가 => CartItem 수량 1증가 (테이블을 사용하든가)
//구현 2.
//카트를 세션으로 구현하고 카트 테이블을 제거해버림 (세션을 사용하든가)
public class CartSession {

    private static CartSession instance;
    private Map<Long, Long> cart = new HashMap<>(); //<itemId, 수량>
    //

    public void addItem(Long itemId) {
        if (cart.containsKey(itemId)) {
            cart.put(itemId, cart.get(itemId)+1);
        } else {
            cart.put(itemId, 1L);
        }
    }

    public CartSession getInstance() {
        if (instance == null) {
            return new CartSession();
        }
        return instance;
    }

    public Map<Long, Long> getCart() {
        return cart;
    }

}
