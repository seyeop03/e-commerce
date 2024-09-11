package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Item {
    private Long itemId;
    private String name;
    private int price;
    private String manufactureDate;
    private String origin;
    private String company;
    private String size;
    private String color;

    public static Item of(String name, int price, String manufactureDate, String origin, String company, String size, String color) {
        return new Item(null,name,price,manufactureDate,origin,company,size,color);
    }
}
