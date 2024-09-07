package domain;

import lombok.Getter;

@Getter
public class Cart {
    private Long cartId; //PK
    private Long memberId; //FK
}
