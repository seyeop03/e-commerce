package config;

import repository.*;
import service.ItemService;
import service.MemberService;
import service.OrderService;

public class AppConfig {

    //싱글톤 인스턴스
    private static AppConfig instance;

    //서비스 인스턴스
    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;

    //객체 미리 로딩
    private AppConfig() {
        MemberRepository memberRepository = new MemberRepository();
        ItemRepository itemRepository = new ItemRepository();
        OrderRepository orderRepository = new OrderRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        CategoryItemRepository categoryItemRepository = new CategoryItemRepository();
        ReviewRepository reviewRepository = new ReviewRepository();

        // 서비스에 리포지토리 주입
        memberService = new MemberService(memberRepository, reviewRepository, itemRepository);
        orderService = new OrderService(orderRepository, itemRepository, orderItemRepository, reviewRepository);
        itemService = new ItemService(itemRepository, orderItemRepository, categoryRepository, categoryItemRepository, reviewRepository, memberRepository);
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public MemberService memberService() {
        return memberService;
    }

    public OrderService orderService() {
        return orderService;
    }

    public ItemService itemService() {
        return itemService;
    }
}
