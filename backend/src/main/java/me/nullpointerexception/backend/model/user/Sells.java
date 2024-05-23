package me.nullpointerexception.backend.model.user;

public class Sells {
    private final String sellId;
    private final String productId;
    private final String userId;
    private final int sellQuantity;

    public Sells(String sellId, String productId, String userId, int sellQuantity) {
        this.sellId = sellId;
        this.productId = productId;
        this.userId = userId;
        this.sellQuantity = sellQuantity;
    }

    public Sells(String sellId, String productId, String userId) {
        this.sellId = sellId;
        this.productId = productId;
        this.userId = userId;
        this.sellQuantity = 0;
    }

    public String getSellId() {
        return sellId;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }
}
