package domain;

import lombok.Getter;

@Getter
public class CategoryItem {
    private Long categoryItemId;
    private Long itemId; //FK
    private Long categoryId; //FK

    public CategoryItem(Long itemId, Long categoryId) {
        this.itemId = itemId;
        this.categoryId = categoryId;
    }
}
