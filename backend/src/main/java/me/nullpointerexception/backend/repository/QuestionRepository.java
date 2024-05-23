package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepository {

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<Question> questionRowMapper = (rs, rowNum) -> new Question(
            UUID.fromString(rs.getString("QuestionID")),
            UUID.fromString(rs.getString("userID")),
            UUID.fromString(rs.getString("ProductID")),
            rs.getLong("QuestionDate"),
            rs.getString("Question")
    );

    public List<Question> getQuestions(String productId) {
        String sql = "SELECT * FROM Question WHERE ProductID = ?";
        return jdbcTemplate.query(sql, questionRowMapper, productId);
    }

    public void addQuestion(Question question) {
        String sql = "INSERT INTO Question(QuestionID,UserID,ProductID,Question,QuestionDate) VALUES (?,?,?,?,?);";
        jdbcTemplate.update(sql, question.getQuestionId().toString(), question.getUserID().toString(), question.getProductId().toString(), question.getQuestion(), question.getQuestionDate());
    }

    public void deleteQuestion(String id) {
        String sql = "DELETE FROM Question WHERE QuestionID = ?";
        jdbcTemplate.update(sql, id);
    }
}
