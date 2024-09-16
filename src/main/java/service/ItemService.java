package service;

import common.*;
import common.UserInput;
import domain.Item;
import domain.Member;
import domain.Review;
import repository.*;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.UserInput.*;

//기본 상품 기능(상품 등록, 상품 삭제, 상품 수정) + 카테고리별 조회(상품 조회, 장바구니, 특정 상품 리뷰)
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public ItemService(ItemRepository itemRepository, OrderItemRepository orderItemRepository, CategoryRepository categoryRepository, CategoryItemRepository categoryItemRepository, ReviewRepository reviewRepository, MemberRepository memberRepository) {
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.categoryRepository = categoryRepository;
        this.categoryItemRepository = categoryItemRepository;
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
    }

    //== 상품 서비스 핸들러 ==//
    public void handleItemService(Scanner sc) {
        while (true) {
            displayItemMenu();
            int choice = inputInt("선택: ", sc);
            CartSession cartSession = CartSession.getInstance();

            if (choice == 1) { //카테고리별 조회 서비스 호출
                viewCategoryService(sc);
            } else if (choice == 2) { //상품 키워드 검색 서비스 호출
                if (!itemSearchServiceWithPagination(sc)) continue;
            } else if (choice == 0) { //뒤로 가기
                return;
            } else {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            /**
             * 카테고리별 조회 서비스, 상품 키워드 검색 이후 공통 로직
             */
            serviceChoice(); //장바구니, 상품 리뷰 조회 중 택 1
            int subChoice = inputInt("선택 : ", sc);

            if (subChoice == 1) { //장바구니 서비스
                Member currentMember = Session.getInstance().getCurrentMember();
                if (currentMember == null) {
                    System.out.println("로그인 이후 장바구니를 담을 수 있습니다.");
                } else {
                    cartService(cartSession, sc);
                }
            } else if (subChoice == 2) { //리뷰 서비스
                reviewService(sc);
            } else if (subChoice == 0) { //뒤로 가기
                return;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private static void displayItemMenu() {
        System.out.println("""
                ================================
                     상품 서비스 메뉴 선택 화면
                ================================
                1. 카테고리별 상품 조회
                2. 상품 키워드 검색
                0. 뒤로 가기
                ================================
                """);
    }

    private void viewCategoryService(Scanner sc) {
        CategoryType.printHighCategories(); //대분류 카테고리 출력
        int highCategoryNum = inputInt("대분류 카테고리 번호 선택: ", sc);
        CategoryType categoryName = CategoryType.fromIndex(highCategoryNum); //인덱스로 대분류 카테고리 열거형 객체 뽑아내기
        CategoryType.printMiddleCategory(categoryName); //선택한 대분류에 대한 중분류 카테고리들을 출력

        int middleCategoryNum = inputInt("중분류 카테고리 번호 선택: ", sc);
        List<String> middleCategoryTypeNames = categoryName.getMiddleCategoryTypeNames();
        String selectedCategoryName = middleCategoryTypeNames.get(--middleCategoryNum);
        Long categoryId = categoryRepository.findIdByCategoryName(selectedCategoryName); //카테고리 명으로 카테고리 ID 조회
        categoryItemRepository.findItemsByCategoryId(categoryId)
                .forEach(item -> viewItemInfo(item));
    }

    private void viewItemInfo(Item item) {
        System.out.println("상품 ID: " + item.getItemId()
                + " | 상품명: " + item.getName()
                + " | 가격: " + item.getPrice()
                + " | 제조일자: " + item.getManufactureDate()
                + " | 제조국가: " + item.getOrigin()
                + " | 제조회사: " + item.getCompany());
    }

    private boolean itemSearchServiceWithPagination(Scanner sc) {
        String keyword = inputString("검색할 키워드를 입력해주세요 : ", sc);
        int pageSize = 10;
        int totalCount = itemRepository.totalCount(keyword); //총 상품 갯수
        int totalPage = (totalCount + pageSize - 1) / pageSize; //총 페이지 수

        if (totalPage == 0) {
            System.out.println("해당 키워드의 상품이 없습니다.");
            return false;
        }

        for (int pageNumber = 1; pageNumber <= totalPage; pageNumber++) {
            List<Item> itemList = itemRepository.findByKeywordWithPagination(keyword, pageNumber, pageSize);

            itemList.forEach(item -> viewItemInfo(item));

            int startPage = (pageNumber-1)/10 * 10 + 1;
            for (int i = startPage; i < startPage + 10 && i <= totalPage; i++) {
                if (i == pageNumber) {
                    System.out.print("\u001B[34m" + i + "\u001B[0m" + " | ");
                } else {
                    System.out.print(i + " | ");
                }
            }
            System.out.println();

            if (pageNumber == totalPage) {
                System.out.println("마지막 페이지입니다.");
                break;
            }
            boolean flag = getUserInputForNextPage(sc);

            if (!flag) break;
        }
        return true;
    }

    private boolean getUserInputForNextPage(Scanner sc) {
        boolean flag = false;
        String userInput;
        while (true) { //입력값 제한 (Y/N)을 위한 loop
            userInput = inputString("다음 페이지를 보시겠습니까? (Y/N): ", sc);
            if (userInput.equalsIgnoreCase("Y")) {
                flag = true;
                break;
            } else if (userInput.equalsIgnoreCase("N")) {
                break;
            }
        }
        return flag;
    }

    private static void serviceChoice() {
        System.out.println("=================================");
        System.out.println("1. 장바구니에 상품 담기");
        System.out.println("2. 상품 리뷰 보기");
        System.out.println("0. 뒤로 가기");
        System.out.println("=================================");
    }

    private void cartService(CartSession cartSession, Scanner sc) {
        Long itemId = inputLong("장바구니에 담을 상품 ID 를 입력해주세요: ",sc);
        Optional<Item> item = itemRepository.findById(itemId);
        item.ifPresentOrElse(
                i -> cartSession.addItem(itemId),
                () -> System.out.println("존재하지 않는 상품 ID 입니다.")
        );
    }

    // (제품 아이디별)
    private void reviewService(Scanner sc) {
        Long itemId = inputLong("리뷰를 보실 상품 ID 를 입력해 주세요 : ", sc);
        System.out.println("""
                정렬기준을 선택해 주세요.
                1. 최신순
                2. 별점 높은순
                3. 별점 낮은순
                """);
        int sortOption = inputInt("선택: ", sc);
        int sort;
        switch (sortOption) {
            case 1:
                sort = 1;
                break;
            case 2:
                sort = 2;
                break;
            case 3:
                sort = 3;
                break;
            default:
                System.out.println("잘못된 입력입니다. 기본값으로 최신순으로 정렬됩니다.");
                sort = 1;
                break;
        }
        itemRepository.findById(itemId)
                .ifPresentOrElse(
                        i -> reviewRepository.findAllByItemIdAndSort(itemId, sort)
                                .forEach(r -> viewReviewInfo(r)),
                        () -> System.out.println("존재하지 않는 상품 ID 입니다.")
                );
    }

    private void viewReviewInfo(Review review) {
        System.out.println(
                "------------------------------------------------------------------------------- \n"
                + "리뷰 ID: " + review.getReviewId()
                + " | 작성자: " + memberRepository.findById(review.getMemberId())
                                            .map(m -> m.getUsername().length() == 1 ? "*" : m.getUsername().replaceAll("^..", "**"))
                                            .orElse("(탈퇴한 유저)")
                + " | 별점: " + "★".repeat(review.getStars())
                + " | 작성 일자: " + review.getDate());
        System.out.println("내용: " + review.getContents() + '\n');
    }

    private void showReviews(Scanner sc) {
        Long itemId = inputLong("리뷰를 확인할 아이템 번호 입력 : ", sc);
        List<Review> reviewList = reviewRepository.findByItemId(itemId);
        for (Review review : reviewList) {
            System.out.println(review);
        }
    }

    private static boolean isAdmin(Member currentMember) {
        return currentMember.getRole().equals(Role.ADMIN);
    }

    private static Item getItem(Scanner sc) {
        String name = inputString("상품 이름: ", sc);
        int price = inputInt("상품 가격: ", sc);
        String manufactureDate = inputString("상품 제조날짜: ", sc);
        String origin = inputString("상품 원산지: ", sc);
        String company = inputString("상품 제조사: ", sc);
        String size = inputString("상품 사이즈: ", sc);
        String color = inputString("상품 색상: ", sc);

        Item item = Item.of(name, price, manufactureDate, origin, company, size, color);

        return item;
    }

}
