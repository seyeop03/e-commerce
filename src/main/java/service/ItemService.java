package service;

import domain.Review;
import common.Session;
import repository.*;

import java.util.Objects;
import java.util.Scanner;

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
        int choice = sc.nextInt();
        sc.nextLine();  // Consume newline

        switch (choice){
            case 1: //상품 관련 메인 서비스 호출 (카테고리별 상품 조회, 장바구니, 특정 상품 리뷰)
                handleItemMainService(sc);
                break;
            case 2: //상품 관리자 서비스 핸들러 호출 (상품 등록, 삭제, 수정 기능)
                handleItemAdminService(sc);
                break;
            default:
                serviceBreak();
        }
    }

    //== 상품 서비스 1. 상품 관련 메인 서비스 핸들러 ==//
    public void handleItemMainService(Scanner sc) {
    }

    //== 상품 서비스 2. 상품 관리자 서비스 핸들러 ==//
    public void handleItemAdminService(Scanner sc) {
        displayAdminMenu();
        int choice = sc.nextInt();
        switch (choice) {
            case 1 :

        }
    }

    private static void displayAdminMenu() {
        System.out.println("1. 상품 등록");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
    }

    //== 리뷰 서비스 핸들러 ==//
    private void handleReviewService(Scanner sc){
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
//        reviewRepository.findxxx();
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
                1. 리뷰서비스
                2. ~~~~
                3. ~~~~
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
