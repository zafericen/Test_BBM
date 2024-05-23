package me.nullpointerexception.backend.pojo;

import java.util.UUID;

public record QuestionInfo(String question, UUID productID, UUID userID) {
}
