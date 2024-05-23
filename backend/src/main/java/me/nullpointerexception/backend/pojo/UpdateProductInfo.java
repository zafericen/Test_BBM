package me.nullpointerexception.backend.pojo;

public class UpdateProductInfo {
    private final String name;
    private final String description;
    private final double price;
    private final String categoryID;
    private final byte[] images = new byte[5]; // 5 images per product

    public UpdateProductInfo(String name, String description, double price, String categoryID) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getCategoryID() {
        return categoryID;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        System.arraycopy(images, 0, this.images, 0, images.length);
    }
}
