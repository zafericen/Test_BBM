package me.nullpointerexception.backend.model.comment;


import java.util.UUID;

public class ProductComment extends Comment {
    private final UUID productID;
    private final int rating;

    public ProductComment(UUID commentID, UUID userID, UUID productID, long commentDate, String comment, byte[][] images, int rating) {
        super(commentID, userID, comment, commentDate, images);
        this.productID = productID;
        this.rating = rating;
    }

    public ProductComment(UUID commentID, UUID userID, UUID productID, long commentDate, String comment, int rating) {
        super(commentID, userID, comment, commentDate);
        this.productID = productID;
        this.rating = rating;
    }

    public ProductComment(UUID userID, UUID productID, String comment, int rating) {
        super(userID, comment);
        this.productID = productID;
        this.rating = rating;
    }

    public UUID getProductID() {
        return productID;
    }

    public int getRating() {
        return rating;
    }
}
