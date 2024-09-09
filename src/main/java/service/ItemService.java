package service;

import common.Role;
import common.UserInput;
import domain.Item;
import domain.Member;
import domain.Review;
import common.Session;
import repository.*;

import java.util.Scanner;
import java.util.stream.Collectors;

import static common.UserInput.*;

//기본 상품 기능(상품 등록, 상품 삭제, 상품 수정) + 카테고리별 조회(상품 조회, 장바구니, 특정 상품 리뷰)
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryItemRepository categoryItemRepository;
    private final ReviewRepository reviewRepository;

    public ItemService(ItemRepository itemRepository, OrderItemRepository orderItemRepository, CategoryRepository categoryRepository, CategoryItemRepository categoryItemRepository, ReviewRepository reviewRepository) {
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.categoryRepository = categoryRepository;
        this.categoryItemRepository = categoryItemRepository;
        this.reviewRepository = reviewRepository;
    }

    //== 상품 서비스 핸들러 ==//
    public void handleItemService(Scanner sc){
        displayItemMenu();
        int choice = inputInt("선택: ", sc);

        switch (choice) {
            case 1: //카테고리별 조회 서비스 호출
                int middleNum = categoryItemViewService();
                itemListView(middleNum); //중분류에 해당하는 상품들 조회
                //장바구니 (장바구니 서비스) => cartService(itemId)
                //리뷰 (1. 리뷰 서비스) => reviewService(itemId)
                break;
            case 2: //상품 키워드 검색 서비스 호출
                itemSearchService();
                //장바구니 (장바구니 서비스) => cartService(itemId)
                //리뷰 (1. 리뷰 서비스) => reviewService(itemId)
                break;
        }

//        switch (choice) {
//            case 1 :
//                majorCategory();
//                int categoryChoice = sc.nextInt();
//                switch (categoryChoice) {
//                    int subChoice;
//                    String keyword;
//                    case 1 :
//                        subChoice = sc.nextInt();
//                        electronMiddleCategory();
//                        switch (subChoice){
//                            case 1 :
//                                keyword = "냉장고";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 2 :
//                                keyword = "TV";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 3 :
//                                keyword = "세탁기";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 4 :
//                                keyword = "청소기";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 5 :
//                                keyword = "전자레인지";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 6 :
//                                keyword = "컴퓨터";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                        }
//                        break;
//                    case 2 :
//                        petMiddleCategory();
//                        subChoice = sc.nextInt();
//                        switch (subChoice) {
//                            case 1:
//                                keyword = "사료";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 2:
//                                keyword = "간식";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 3:
//                                keyword = "용품";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                        }
//                        break;
//                    case 3 :
//                        clothesMiddleCategory();
//                        subChoice = sc.nextInt();
//                        switch (subChoice) {
//                            case 1:
//                                keyword = "상의";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 2:
//                                keyword = "하의";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 3:
//                                keyword = "신발";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 4:
//                                keyword = "속옷";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 5:
//                                keyword = "점퍼";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                            case 6:
//                                keyword = "가방/악세서리";
//                                itemRepository.findByKeyword(keyword);
//                                break;
//                        }
//                    case 4 :
//                        foodMiddleCategory();
//                        break;
//                    case 5 :
//                        livingMiddleCategory();
//                        break;
//                    case 6 :
//                        sportsMiddleCategory();
//                        break;
//                    case 7 :
//                        phraseMiddleCategory();
//                        break;
//                    case 8 :
//                        bookMiddleCategory();
//                        break;
//                }
//                break;
//            case 2 :
//                System.out.println("검색어를 입력해주세요.");
//                String keyword = sc.next();
//                itemRepository.findByKeyword(keyword);
//                break;
//            case 3 :
//                handleReviewService(sc);
//                break;
//        }
    }

    private void itemSearchService() {
    }

    private void itemListView(int middleNum) {

    }

    private int categoryItemViewService() {

    }

    private static boolean isAdmin(Member currentMember) {
        return currentMember.getRole().equals(Role.ADMIN);
    }

    private static void bookMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 소설");
        System.out.println("2. 경제/경영");
        System.out.println("3. IT");
        System.out.println("4. 예술");
        System.out.println("5. 기술/공학");
        System.out.println("6. 유아");
    }

    private static void phraseMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 필기구");
        System.out.println("2. 노트/메모지");
        System.out.println("3. 유아");
    }

    private static void sportsMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 구기");
        System.out.println("2. 헬스/요가");
        System.out.println("3. 라켓스포츠");
        System.out.println("4. 수영");
        System.out.println("5. 낚시");
    }

    private static void livingMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 잡화");
        System.out.println("2. 세제");
        System.out.println("3. 수납/정리");
        System.out.println("4. 바디/헤어/구강/면도");
        System.out.println("5. 가구/조명/인테리어");
    }

    private static void foodMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 과일");
        System.out.println("2. 축산");
        System.out.println("3. 수산물/건어물");
        System.out.println("4. 냉장/냉동/간편요리");
        System.out.println("5. 쌀/잡곡");
        System.out.println("6. 커피/원두/차");
    }

    private static void clothesMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 상의");
        System.out.println("2. 하의");
        System.out.println("3. 신발");
        System.out.println("4. 속옷");
        System.out.println("5. 점퍼");
        System.out.println("6. 가방/악세서리");
    }

    private static void petMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 사료");
        System.out.println("2. 간식");
        System.out.println("3. 용품");
    }

    private static void electronMiddleCategory() {
        System.out.println("===== 중분류 =====");
        System.out.println("1. 냉장고");
        System.out.println("2. TV");
        System.out.println("3. 세탁기");
        System.out.println("4. 청소기");
        System.out.println("5. 전자레인지");
        System.out.println("6. 컴퓨터");
    }

    private static void majorCategory() {
        System.out.println("===== 대분류 =====");
        System.out.println("1. 가전/디지털");
        System.out.println("2. 반려동물용품");
        System.out.println("3. 의류");
        System.out.println("4. 식품");
        System.out.println("5. 생활용품");
        System.out.println("6. 스포츠/레저");
        System.out.println("7. 문구/완구");
        System.out.println("8. 도서");
    }

    private static void displayUserMenu() {
        System.out.println("1. 카테고리별 상품 조회");
        System.out.println("2. 상품 키워드 검색");
        System.out.println("3. 장바구니");
        System.out.println("4. 리뷰 서비스");
    }

    private static Item getItem(Scanner sc) {
        String name = inputString("상품 이름: ", sc);
        int price = inputInt("상품 가격: ", sc);
        String manufactureDate = inputString("상품 제조날짜: ", sc);
        String origin = inputString("상품 원산지: ", sc);
        String company = inputString("상품 제조사: ", sc);
        String size = inputString("상품 사이즈: ", sc);
        String color = inputString("상품 색상: ", sc);

        Item item = Item.of(name,price,manufactureDate,origin,company,size,color);

        return item;
    }

    //== 리뷰 서비스 ==//
    public void handleReviewService(Scanner sc){
        int choice = sc.nextInt();
        displayReviewMenu();

        switch (choice){
            case 1: //리뷰보기
                selectReview();
                break;
            case 2: //리뷰작성
                insertReview(sc);
                break;
            case 3: //리뷰수정
                updateReview(sc);
                break;
            case 4: //리뷰삭제
                deleteReview(sc);
                break;
            default:
                serviceBreak();
        }
    }

    private void selectReview() {
        // 권한 확인
        Long memberId = Session.getInstance().getCurrentMember().getMemberId();
        reviewRepository.findByMemberId(memberId);
    }

    private void deleteReview(Scanner sc) {
        System.out.println("삭제할 리뷰의 아이디를 입력해 주세요.");
        Long reviewId = sc.nextLong();
        // 권한 확인
        Long memberId = Session.getInstance().getCurrentMember().getMemberId();


        reviewRepository.deleteById(reviewId);
    }

    private void insertReview(Scanner sc) {
        System.out.println("리뷰를 남기실 상품 ID를 입력해주세요.");
        Long itemId = sc.nextLong();
        //해당 상품을 구매한 이력이 있는지 체크
        //이것은 리뷰테이블과는 관계가 없는 회원의 아이디
        Long memberId = Session.getInstance().getCurrentMember().getMemberId();

        boolean isOrdered = orderItemRepository.existsByItemIdAndMemberId(itemId, memberId);

        //성공 시
        if (isOrdered){
            //성공 시
            System.out.println("평점을 입력해주세요.");
            int stars = sc.nextInt();
            System.out.println("내용을 입력해주세요.");
            String contents = sc.next();

            Review review = Review.of(stars, contents,memberId,itemId);
            reviewRepository.save(review);
        }else {
            //실패 시
            System.out.println("해당상품에 대한 리뷰를 작성하실 수 없습니다.");
        }
    }

    private void updateReview(Scanner sc) {
//        System.out.println("수정할 리뷰 아이디를 입력해주세요.");
//        Long reviewId = sc.nextLong();
//        // 권한 확인
//        Long memberId = Session.getInstance().getCurrentMember().getMemberId();
//
//        Review findReview = reviewRepository.findById(reviewId);
//
//        //해당 리뷰 작성자가 본인이 맞을 경우
//        if (Objects.equals(findReview.getMemberId(), memberId)) {
//            System.out.println("수정할 별점을 입력해 주세요.");
//            int stars = sc.nextInt();
//            System.out.println("수정할 리뷰 내용을 입력해주세요.");
//            String contents = sc.next();
//
//            Review review = Review.of(reviewId, stars, contents);
//            reviewRepository.updateById(reviewId, review);
//        }
    }

    private static void displayItemMenu() {
        System.out.println("""
                1. 카테고리별 상품 조회
                2. 상품 키워드 검색
                """);
    }

    private static void displayReviewMenu() {
        System.out.println("""
                1. 리뷰보기
                2. 리뷰작성
                3. 리뷰수정
                4. 리뷰삭제
                """);
    }
    
    private static void serviceBreak() {
        System.out.println("잘못된 번호 입니다.");
    }

}
