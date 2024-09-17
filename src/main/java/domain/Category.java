package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Category {

    private Long categoryId; //PK
    private String categoryName; //카테고리명
    private Long parentCategory;//부모 카테고리 ID 셀프조인

    public Category(String categoryName, Long parentCategory) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
    }
}
