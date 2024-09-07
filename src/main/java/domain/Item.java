package domain;

import lombok.Getter;

@Getter
public class Item {
    private Long itemId;
    private String name;
    private int price;
    private String manufactureDate;
    private String origin;
    private String company;
    private String size;
    private String color;

    public Item(String name, int price, String manufactureDate, String origin, String company, String size, String color) {
        this.name = name;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.origin = origin;
        this.company = company;
        this.size = size;
        this.color = color;
    }
}
