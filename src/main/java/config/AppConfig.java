package config;

import repository.*;
//import service.ItemAdminService;
import service.ItemService;
import service.MemberService;
import service.OrderService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//애플리케이션의 실제 동작에 필요한 구현 객체를 생성하는 책임/역할을 분리하였다.
//But, 중복이 존재하고, 역할에 따른 구현이 잘 보이지 않는다.
public class AppConfig {

    private static AppConfig instance;

    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;
//    private final ItemAdminService itemAdminService;

    private static final Map<String, Object> repositoryManager = new ConcurrentHashMap<>();

    static {
        //리포지토리 인스턴스 생성 및 등록
        MemberRepository memberRepository = new MemberRepository();
        ItemRepository itemRepository = new ItemRepository();
        OrderRepository orderRepository = new OrderRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        CategoryItemRepository categoryItemRepository = new CategoryItemRepository();
        ReviewRepository reviewRepository = new ReviewRepository();

        repositoryManager.put("MemberRepository", memberRepository);
        repositoryManager.put("ItemRepository", itemRepository);
        repositoryManager.put("OrderRepository", orderRepository);
        repositoryManager.put("OrderItemRepository", orderItemRepository);
        repositoryManager.put("CategoryRepository", categoryRepository);
        repositoryManager.put("CategoryItemRepository", categoryItemRepository);
        repositoryManager.put("ReviewRepository", reviewRepository);
    }

    private AppConfig() {
        // 의존성 주입
        memberService = new MemberService(
                (MemberRepository) repositoryManager.get("MemberRepository"),
                (ReviewRepository) repositoryManager.get("ReviewRepository")
        );
        orderService = new OrderService(
                (OrderRepository) repositoryManager.get("OrderRepository"),
                (ItemRepository) repositoryManager.get("ItemRepository"),
                (OrderItemRepository) repositoryManager.get("OrderItemRepository"),
                (ReviewRepository) repositoryManager.get("ReviewRepository")

        );
        itemService = new ItemService(
                (ItemRepository) repositoryManager.get("ItemRepository"),
                (OrderItemRepository) repositoryManager.get("OrderItemRepository"),
                (CategoryRepository) repositoryManager.get("CategoryRepository"),
                (CategoryItemRepository) repositoryManager.get("CategoryItemRepository"),
                (ReviewRepository) repositoryManager.get("ReviewRepository")
        );
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ItemService getItemService() {
        return itemService;
    }

//    public ItemAdminService getItemAdminService() {
//        return itemAdminService;
//    }

//    public MemberService memberService() {
//        return new MemberService(new MemberRepository());
//    }
//
//    public OrderService orderService() {
//        return new OrderService(
//                new OrderRepository(),
//                new MemberRepository(),
//                new CartRepository(),
//                new CartItemRepository(),
//                new ItemRepository(),
//                new OrderItemRepository());
//    }
//
//    public ItemService itemService() {
//        return new ItemService(
//                new ItemRepository(),
//                new OrderItemRepository(),
//                new CategoryRepository(),
//                new CategoryItemRepository(),
//                new ReviewRepository()
//        );
//    }
}
