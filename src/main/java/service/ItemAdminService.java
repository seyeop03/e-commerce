//package service;
//
//import domain.CartItem;
//import domain.Item;
//import repository.CategoryItemRepository;
//import repository.ItemRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class ItemAdminService {
//
//    private final ItemRepository itemRepository;
//    private final CategoryItemRepository categoryItemRepository;
//
//    public ItemAdminService(ItemRepository itemRepository, CategoryItemRepository categoryItemRepository) {
//        this.itemRepository = itemRepository;
//        this.categoryItemRepository = categoryItemRepository;
//    }
//
//    //== 상품 관리자 서비스 핸들러 ==//
//    public void handleItemAdminService(Scanner sc) {
//        displayItemAdminMenu();
//        int choice = sc.nextInt();
//        sc.nextLine();
//
//        switch (choice){
//            case 1: //상품 등록
//                createItem(sc);
//                break;
//            case 2: //상품 수정
//                updateItem(sc);
//                break;
//            case 3: //상품 삭제
//                deleteItem(sc);
//                break;
//            case 0: //돌아가기
//                System.out.println("상품 관리자 서비스를 종료합니다.");
//                return;
//            default:
//                System.out.println("다시 입력해주세요.");
//        }
//    }
//
//    private void displayItemAdminMenu() {
//        System.out.println("1. 상품 등록");
//        System.out.println("2. 상품 전체 조회"); //상품 번호 순으로 페이징
//        System.out.println("3. 상품 수정");
//        System.out.println("4. 상품 삭제");
//        System.out.println("0. 돌아가기");
//    }
//
//    //== 상품 생성 (카테고리 연결 포함) ==//
//    private void createItem(Scanner sc) {
//        System.out.print("상품명: ");
//        String name = sc.next();
//        System.out.print("가격: ");
//        int price = sc.nextInt();
//        System.out.print("제조일자: ");
//        String manufactureDate = sc.next();
//        System.out.print("원산지: ");
//        String origin = sc.next();
//        System.out.print("제조회사: ");
//        String company = sc.next();
//        System.out.print("사이즈: ");
//        String size = sc.next();
//        System.out.print("색상: ");
//        String color = sc.next();
//
//        Item item = Item.of(name, price, manufactureDate, origin, company, size, color);
//        itemRepository.save(item);
//    }
//
//    //== 상품 삭제 ==//
//    private void deleteItem(Scanner sc) {
//        List<CartItem> cartItems = new ArrayList<>();
//
//        for (CartItem cartItem : cartItems) {
//            cartItem.getItemId();
//        }
//    }
//
//    //== 상품 수정 ==//
//    private void updateItem(Scanner sc) {
//    }
//}
