package me.nullpointerexception.backend.model.question;

import java.util.UUID;

public class Question {
    private final UUID questionId;
    private final UUID productId;
    private final UUID userID;
    private final long questionDate;
    private final String question;

    public Question(UUID questionId, UUID productId, UUID userID, long questionDate, String question) {
        this.questionId = questionId;
        this.productId = productId;
        this.userID = userID;
        this.questionDate = questionDate;
        this.question = question;
    }

    public Question(UUID productId, UUID userID, String question) {
        this.questionId = UUID.randomUUID();
        this.productId = productId;
        this.userID = userID;
        this.questionDate = System.currentTimeMillis();
        this.question = question;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getUserID() {
        return userID;
    }

    public long getQuestionDate() {
        return questionDate;
    }

    public String getQuestion() {
        return question;
    }
}
