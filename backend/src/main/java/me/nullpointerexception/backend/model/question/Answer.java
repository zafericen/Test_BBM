package me.nullpointerexception.backend.model.question;

import java.util.UUID;

public class Answer {
    private final UUID userId;
    private final UUID productId;
    private final UUID questionId;
    private final long answerDate;
    private final String answer;

    public Answer(UUID userId, UUID productId, UUID questionId, long answerDate, String answer) {
        this.userId = userId;
        this.productId = productId;
        this.questionId = questionId;
        this.answerDate = answerDate;
        this.answer = answer;
    }

    public Answer(UUID userId, UUID productId, UUID questionId, String answer) {
        this.userId = userId;
        this.productId = productId;
        this.questionId = questionId;
        this.answerDate = System.currentTimeMillis();
        this.answer = answer;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public long getAnswerDate() {
        return answerDate;
    }

    public String getAnswer() {
        return answer;
    }
}
