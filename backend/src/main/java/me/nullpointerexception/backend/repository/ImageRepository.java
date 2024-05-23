package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ImageRepository {

        RowMapper<Image> imageRowMapper = (rs, rowNum) -> new Image(
                UUID.fromString(rs.getString("imageID")),
                rs.getBytes("image")
        );

        @Autowired JdbcTemplate jdbcTemplate;

        public void addImage(UUID imageID, byte[] image) {
            try {
                String sql = "INSERT INTO Image (imageID, image) VALUES (?, ?)";
                jdbcTemplate.update(sql, imageID.toString(), image);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Image getImage(UUID imageID) {
            try {
                String sql = "SELECT * FROM Image WHERE imageID = ?";
                return jdbcTemplate.queryForObject(sql, new Object[]{imageID.toString()}, imageRowMapper);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void deleteImage(UUID imageID) {
            try {
                String sql = "DELETE FROM Image WHERE imageID = ?";
                jdbcTemplate.update(sql, imageID.toString());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
}
