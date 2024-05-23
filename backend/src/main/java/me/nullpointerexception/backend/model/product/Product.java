package me.nullpointerexception.backend.model.product;

import java.util.Arrays;
import java.util.UUID;

public class Product {
    private final UUID productID;
    private final String name;
    private final String description;
    private final double price;
    private final int saleNumber;
    private final double averageRating;
    private final UUID categoryID;
    private byte[][] images;

    /**
     * Constructor for already existing products in the database
     */
    public Product(UUID productID, String name, String description, double price, int saleNumber, UUID categoryID, double averageRating) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.saleNumber = saleNumber;
        this.categoryID = categoryID;
        this.averageRating = averageRating;
        this.images = new byte[5][];
    }

    public Product(UUID productID, String name, String description, double price, int saleNumber, UUID categoryID, double averageRating, byte[][] images) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.saleNumber = saleNumber;
        this.categoryID = categoryID;
        this.averageRating = averageRating;
        this.images = images;
    }

    /**
     * Constructor for new products
     */
    public Product(String name, String description, double price, String category) {
        this.productID = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.saleNumber = 0;
        this.categoryID = UUID.fromString(category);
        this.averageRating = 1;
        this.images = new byte[5][];
    }

    public UUID getProductID() {
        return productID;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getRating() {
        return averageRating;
    }
    public double getPrice() {
        return price;
    }
    public int getSaleNumber() {
        return saleNumber;
    }
    public UUID getCategoryID() {
        return categoryID;
    }
    public byte[][] getImages() {
        return images;
    }
    public void setImages(byte[][] images) {
        this.images = images;
    }
    public void removeImage(int index) {
        this.images[index] = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && saleNumber == product.saleNumber && Double.compare(product.averageRating, averageRating) == 0 && productID.equals(product.productID) && name.equals(product.name) && description.equals(product.description) && categoryID.equals(product.categoryID) && Arrays.equals(images, product.images);
    }
}
