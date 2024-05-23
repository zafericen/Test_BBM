package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.image.CommentImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CommentImageRepository {

    ImageRepository imageRepository;

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<CommentImage> commentImageRowMapper = (rs, rowNum) -> new CommentImage(
            UUID.fromString(rs.getString("commentID")),
            UUID.fromString(rs.getString("imageID"))
    );

    public void addCommentImage(UUID commentID, UUID imageID) {
        try {
            String sql = "INSERT INTO CommentImage (commentID, imageID) VALUES (?, ?)";
            jdbcTemplate.update(sql, commentID.toString(), imageID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommentImage getCommentImage(String commentID) {
        try {
            String sql = "SELECT * FROM CommentImage WHERE commentID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{commentID}, commentImageRowMapper);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteCommentImage(UUID commentID) {
        try {
            String sql = "SELECT * FROM CommentImage WHERE commentID = ?";
            for (CommentImage commentImage : jdbcTemplate.query(sql, new Object[]{commentID}, commentImageRowMapper)) {
                imageRepository.deleteImage(commentImage.getImageID());
            }
            sql = "DELETE FROM CommentImage WHERE commentID = ?";
            jdbcTemplate.update(sql, commentID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCommentImage(UUID commentID, UUID imageID) {
        try {
            String sql = "SELECT * FROM CommentImage WHERE commentID = ?";
            if (jdbcTemplate.queryForObject(sql, new Object[]{commentID}, commentImageRowMapper).getImageID().equals(imageID)) {
                imageRepository.deleteImage(imageID);
            }
            sql = "DELETE FROM CommentImage WHERE commentID = ? AND imageID = ?";
            jdbcTemplate.update(sql, commentID.toString(), imageID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
