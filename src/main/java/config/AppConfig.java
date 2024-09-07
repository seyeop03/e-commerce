package config;

import repository.*;
import service.ItemService;
import service.MemberService;
import service.OrderService;

//애플리케이션의 실제 동작에 필요한 구현 객체를 생성하는 책임/역할을 분리하였다.
//But, 중복이 존재하고, 역할에 따른 구현이 잘 보이지 않는다.
public class AppConfig {

    public MemberService memberService() {
        return new MemberService(new MemberRepository());
    }

    public OrderService orderService() {
        return new OrderService(
                new OrderRepository(),
                new MemberRepository(),
                new CartRepository(),
                new CartItemRepository(),
                new ItemRepository(),
                new OrderItemRepository());
    }

    public ItemService itemService() {
        return new ItemService(
                new ItemRepository(),
                new CategoryRepository(),
                new CategoryItemRepository(),
                new ReviewRepository()
        );
    }
}
