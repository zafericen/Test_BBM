package me.nullpointerexception.backend.model.image;

import java.util.UUID;

public class ProductImage extends Image {

    private final UUID productID;

    public ProductImage(UUID imageID, UUID productID, byte[] image) {
        super(imageID, image);
        this.productID = productID;
    }

    public ProductImage(UUID imageID, UUID productID) {
        super(imageID);
        this.productID = productID;
    }

    public UUID getProductID() {
        return productID;
    }
}
