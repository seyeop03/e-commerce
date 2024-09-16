package common;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum CategoryType {
    ELECTRON("가전/디지털", Arrays.asList("냉장고", "TV", "세탁기", "청소기", "전자레인지", "컴퓨터")),
    PET("반려동물용품", Arrays.asList("사료", "간식", "용품")),
    CLOTHES("의류", Arrays.asList("상의", "하의", "신발", "속옷", "점퍼", "가방/악세서리")),
    FOOD("식품", Arrays.asList("과일", "축산", "수산물/건어물", "냉장/냉동/간편요리", "쌀/잡곡", "커피/원두/차")),
    LIVING("생활용품", Arrays.asList("잡화", "세제", "수납/정리", "바디/헤어/구강/면도", "가구/조명/인테리어")),
    SPORTS("스포츠/레져", Arrays.asList("구기", "헬스/요가", "라켓스포츠", "수영", "낚시")),
    PHRASE("문구/완구", Arrays.asList("필기구", "노트/메모지", "유아")),
    BOOK("도서", Arrays.asList("소설", "경제/경영", "IT", "예술", "기술/공학", "유아"));

    private final String highCategoryTypeName; //대분류 카테고리 이름
    private final List<String> middleCategoryTypeNames; //중분류 카테고리 이름

    CategoryType(String highCategoryType, List<String> middleCategoryTypes) {
        this.highCategoryTypeName = highCategoryType;
        this.middleCategoryTypeNames = middleCategoryTypes;
    }

    //printCategories(CategoryType.values())
    public static void printHighCategories() {
        CategoryType[] CategoryTypes = CategoryType.values();
        int i = 1;
        System.out.print("""
                ===============================================
                                  대분류 카테고리
                ===============================================
                """);
        for (CategoryType CategoryType : CategoryTypes) {
            System.out.println(i + ". " + CategoryType.getHighCategoryTypeName());
            i++;
        }
        System.out.println("===============================================");
    }

    //printCategory(CategoryType.BOOK)
    public static void printMiddleCategory(CategoryType highCategoryType) {
        System.out.print("""
                ===============================================
                                  중분류 카테고리
                ===============================================
                """);
        List<String> items = highCategoryType.getMiddleCategoryTypeNames();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
        System.out.println("===============================================");
    }

    //인덱스로부터 카테고리 타입 추출
    public static CategoryType fromIndex(int index) {
        if (index < 1 || index > CategoryType.values().length) {
            throw new IndexOutOfBoundsException();
        }
        return CategoryType.values()[--index];
    }
}
