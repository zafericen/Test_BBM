package me.nullpointerexception.backend.model.favourites;

public class Favourites {
    String userID;
    String productID;

    public Favourites(String userID, String productID) {
        this.userID = userID;
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public String getProductID() {
        return productID;
    }
}
