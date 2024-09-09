package service;

import common.UserInput;
import domain.Review;
import common.Session;
import repository.*;

import java.util.Objects;
import java.util.Scanner;

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
        int choice = sc.nextInt();
        sc.nextLine();  // Consume newline

        switch (choice){
            case 1: //상품 관련 메인 서비스 호출 (카테고리별 상품 조회, 장바구니, 특정 상품 리뷰)
                //(주방 > 냄비 ,후라이 등등. .> 제품 선택하면 > 제품 데이터 + (제품 id에 대한)리뷰, 장바구니 담기)
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
    }

    //== 리뷰 서비스 핸들러 ==//
    private void handleReviewService(Scanner sc){
        int choice = sc.nextInt();
        displayReviewMenu();

        switch (choice){
            case 1: //리뷰보기
                selectReview(sc);
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

    // (제품 아이디별) 
    private void selectReview(Scanner sc) {
        Long itemId = inputLong("리뷰 보실 제품의 아이디를 입력해 주세요 :", sc);
        System.out.println("""
                정렬기준을 선택해 주세요.
                1. 최신순
                2. 별점 높은순
                3. 별점 낮은순
                """);
        int sortOption = sc.nextInt();
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
        reviewRepository.findByAll(itemId, sort);
    }

//    (회원(memberID)별 리뷰보기 완료)
//    private void memberIdRewview(){
//        Long memberId = Session.getInstance().getCurrentMember().getMemberId();
//        reviewRepository.findReviewById(memberId);
//    }

    // (리뷰 삭제하기 완료)
    private void deleteReview(Scanner sc) {
        Long reviewId = inputLong("삭제할 리뷰의 아이디를 입력해 주세요 :", sc);
        // 권한 확인
        Long memberId = Session.getInstance().getCurrentMember().getMemberId();
        boolean findMemberID = reviewRepository.findById(reviewId, memberId);

        if(findMemberID){
            reviewRepository.deleteById(reviewId);
            System.out.println("리뷰가 삭제 되었습니다.");
        }else{
            System.out.println("삭제할 수 있는 리뷰가 없습니다.");
        }
    }

    // (리뷰 작성하기 완료)
    private void insertReview(Scanner sc) {
        Long itemId = inputLong("리뷰를 남기실 상품 ID를 입력해주세요:", sc);
        //해당 상품을 구매한 이력이 있는지 체크
        //이것은 리뷰테이블과는 관계가 없는 회원의 아이디
        Long memberId = Session.getInstance().getCurrentMember().getMemberId();
        boolean isOrdered = orderItemRepository.existsByItemIdAndMemberId(itemId, memberId);

        if (isOrdered){
            //성공 시
            int stars = inputInt("평점을 입력해주세요: ", sc);
            String contents = inputString("내용을 입력해주세요: ", sc);

            Review review = Review.of(stars,contents,memberId,itemId);
            reviewRepository.save(review);
        }else {
            //실패 시
            System.out.println("해당상품에 대한 리뷰를 작성하실 수 없습니다.");
        }
    }

    // (리뷰 수정하기 완료)
    private void updateReview(Scanner sc) {
        Long reviewId = inputLong("수정할 리뷰의 아이디를 입력해주세요 :", sc);
        Long memberId = Session.getInstance().getCurrentMember().getMemberId();
        boolean findMemberID = reviewRepository.findById(reviewId, memberId);

        //해당 리뷰 작성자가 본인이 맞을 경우
        if (findMemberID) {
            int stars = inputInt("수정할 별점을 입력해 주세요 :", sc);
            String contents = inputString("수정할 리뷰 내용을 입력해주세요 :", sc);

            Review review = Review.of(stars, contents);
            reviewRepository.updateById(reviewId, review);
            System.out.println("리뷰가 수정 되었습니다.");
        }
        else {
            System.out.println("수정할 리뷰가 없습니다.");
        }
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
