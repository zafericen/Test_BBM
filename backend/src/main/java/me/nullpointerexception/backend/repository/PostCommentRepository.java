package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.comment.PostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostCommentRepository {

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<PostComment> postCommentRowMapper = (rs, rowNum) -> new PostComment(
            UUID.fromString(rs.getString("commentID")),
            UUID.fromString(rs.getString("userID")),
            UUID.fromString(rs.getString("postID")),
            rs.getLong("commentDate"),
            rs.getString("comment")
    );

    public List<PostComment> getComments(String id) {
        String sql = "SELECT * FROM PostComment WHERE postID = ?";
        try {
            return jdbcTemplate.query(sql, postCommentRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    public void addComment(PostComment postComment) {
        String sql = "INSERT INTO PostComment(commentID, userID, postID, commentDate, comment) VALUES (?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, postComment.getCommentID().toString(), postComment.getUserID().toString(), postComment.getPostID().toString(),
                    postComment.getCommentDate(), postComment.getComment());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeComment(String id) {
        String sql = "DELETE FROM PostComment WHERE commentID = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateComment(PostComment postComment) {
        String sql = "UPDATE PostComment SET comment = ? WHERE commentID = ?";
        try {
            jdbcTemplate.update(sql, postComment.getComment(), postComment.getCommentID().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
