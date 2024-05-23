package me.nullpointerexception.backend.model.user;

public class UserCategoryInterest {
    private final String userId;
    private final String categoryId;
    private float interest = 1.0f;

    public UserCategoryInterest(String userId, String categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }
}
