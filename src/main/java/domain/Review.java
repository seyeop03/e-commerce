package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {

    private Long reviewId; //PK
    private int star; //별점
    private String contents; //리뷰 내용
    private String date; //날짜
    private Long memberId; //FK
    private Long itemId; //FK

    //== 정적 팩토리 메서드 ==//
    public static Review of(int star, String contents){
        return new Review(null,star,contents,null,null,null);
    }

    public static Review of(int star, String contents, Long memberId, Long itemId){
        return new Review(null,star,contents,null,memberId,itemId);
    }
}
