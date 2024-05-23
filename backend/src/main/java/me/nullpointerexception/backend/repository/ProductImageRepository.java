package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.image.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductImageRepository {

    ImageRepository imageRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<ProductImage> productImageRowMapper = (rs, rowNum) -> new ProductImage(
            UUID.fromString(rs.getString("productID")),
            UUID.fromString(rs.getString("imageID"))
    );

    public void addProductImage(UUID productID, UUID imageID) {
        try {
            String sql = "INSERT INTO ProductImage (productID, imageID) VALUES (?, ?)";
            jdbcTemplate.update(sql, productID.toString(), imageID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductImage getProductImage(UUID productID) {
        try {
            String sql = "SELECT * FROM ProductImage WHERE productID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{productID}, productImageRowMapper);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteProductImage(UUID productID) {
        try {
            String sql = "SELECT * FROM ProductImage WHERE productID = ?";
            for (ProductImage productImage : jdbcTemplate.query(sql, new Object[]{productID}, productImageRowMapper)) {
                imageRepository.deleteImage(productImage.getImageID());
            }
            sql = "DELETE FROM ProductImage WHERE productID = ?";
            jdbcTemplate.update(sql, productID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductImage(UUID productID, UUID imageID) {
        try {
            String sql = "SELECT * FROM ProductImage WHERE productID = ? AND imageID = ?";
            if (!jdbcTemplate.query(sql, new Object[]{productID, imageID}, productImageRowMapper).isEmpty()) {
                    imageRepository.deleteImage(imageID);
            }
            sql = "DELETE FROM ProductImage WHERE productID = ? AND imageID = ?";
            jdbcTemplate.update(sql, productID.toString(), imageID.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
