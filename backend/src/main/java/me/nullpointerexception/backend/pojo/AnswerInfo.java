package me.nullpointerexception.backend.pojo;

import java.util.UUID;

public record AnswerInfo(UUID UserID, UUID ProductID, UUID QuestionID, String Answer) {
}
