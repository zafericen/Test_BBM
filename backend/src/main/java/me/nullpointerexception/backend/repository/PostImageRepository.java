package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.image.PostImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PostImageRepository {

    ImageRepository imageRepository;

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<PostImage> postImageRowMapper = (rs, rowNum) -> new PostImage(
            UUID.fromString(rs.getString("postID")),
            UUID.fromString(rs.getString("imageID"))
    );

    public void addPostImage(UUID postID, UUID imageID) {
        try {
            String sql = "INSERT INTO PostImage (postID, imageID) VALUES (?, ?)";
            jdbcTemplate.update(sql, postID.toString(), imageID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PostImage getPostImage(UUID postID) {
        try {
            String sql = "SELECT * FROM PostImage WHERE postID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{postID}, postImageRowMapper);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletePostImage(UUID postID) {
        try {
            String sql = "SELECT * FROM PostImage WHERE postID = ?";
            for (PostImage postImage : jdbcTemplate.query(sql, new Object[]{postID}, postImageRowMapper)) {
                imageRepository.deleteImage(postImage.getImageID());
            }
            sql = "DELETE FROM PostImage WHERE postID = ?";
            jdbcTemplate.update(sql, postID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePostImage(UUID postID, UUID imageID) {
        try {
            String sql = "SELECT * FROM PostImage WHERE postID = ?";
            if (jdbcTemplate.queryForObject(sql, new Object[]{postID}, postImageRowMapper).getImageID().equals(imageID)) {
                imageRepository.deleteImage(imageID);
            }
            sql = "DELETE FROM PostImage WHERE postID = ? AND imageID = ?";
            jdbcTemplate.update(sql, postID.toString(), imageID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




}
