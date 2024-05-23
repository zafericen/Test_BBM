package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.question.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AnswerRepository {

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<Answer> answerRowMapper =  (rs, rowNum) -> new Answer(
            UUID.fromString(rs.getString("userID")),
            UUID.fromString(rs.getString("ProductID")),
            UUID.fromString(rs.getString("QuestionID")),
            rs.getLong("AnswerDate"),
            rs.getString("Answer")
    );

    public Answer getAnswer(String questionId) {
        String sql = "SELECT * FROM Answer WHERE QuestionID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, answerRowMapper, questionId);
        } catch (Exception e) {
            return null;
        }
    }

    public void addAnswer(Answer answer) {
        String sql = "INSERT INTO Answer(UserID,ProductID,QuestionID,AnswerDate,Answer) VALUES (?,?,?,?,?);";
        jdbcTemplate.update(sql, answer.getUserId().toString(), answer.getProductId().toString(), answer.getQuestionId().toString(), answer.getAnswerDate(), answer.getAnswer());
    }

    public void deleteAnswer(String id) {
        String sql = "DELETE FROM Answer WHERE QuestionID = ?";
        jdbcTemplate.update(sql, id);
    }
}
