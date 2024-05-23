package me.nullpointerexception.backend.pojo;

import java.util.UUID;

public class AddProductInfo {
    private final String name;
    private final String description;
    private final double price;
    private final int saleNumber;
    private final String categoryID;
    private final byte[] images = new byte[5]; // 5 images per product

    public AddProductInfo(String name, String description, double price, int saleNumber, String categoryID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.saleNumber = saleNumber;
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public UUID getCategory() {
        return UUID.fromString(categoryID);
    }
    public byte[] getImages() {
        return images;
    }
}
