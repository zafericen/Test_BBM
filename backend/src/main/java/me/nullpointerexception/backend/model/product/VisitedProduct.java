package me.nullpointerexception.backend.model.product;

public class VisitedProduct {
    private final String userId;
    private final String productId;
    private final long visitDate;

    public VisitedProduct(String userId, String productId, long visitDate) {
        this.userId = userId;
        this.productId = productId;
        this.visitDate = visitDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public long getVisitDate() {
        return visitDate;
    }
}
