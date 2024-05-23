package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.comment.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductCommentRepository {

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<ProductComment> productCommentRowMapper = (rs, rowNum) -> new ProductComment(
            UUID.fromString(rs.getString("commentID")),
            UUID.fromString(rs.getString("userID")),
            UUID.fromString(rs.getString("productID")),
            rs.getLong("commentDate"),
            rs.getString("comment"),
            rs.getInt("rating")
    );

    public List<ProductComment> getComments(String id) {
        String sql = "SELECT * FROM ProductComment WHERE productID = ?";
        try {
            return jdbcTemplate.query(sql, productCommentRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    public void addComment(ProductComment productComment) {
        String sql = "INSERT INTO ProductComment(commentID, userID, productID, commentDate, comment, rating) VALUES (?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, productComment.getCommentID().toString(), productComment.getUserID().toString(), productComment.getProductID().toString(),
                    productComment.getCommentDate(), productComment.getComment(), productComment.getRating());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeComment(String id) {
        String sql = "DELETE FROM ProductComment WHERE commentID = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateComment(ProductComment productComment) {
        String sql = "UPDATE ProductComment SET comment = ?, rating = ? WHERE commentID = ?";
        try {
            jdbcTemplate.update(sql, productComment.getComment(), productComment.getRating(), productComment.getCommentID().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
