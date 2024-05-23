package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {

    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
            UUID.fromString(rs.getString("productID")),
            rs.getString("Name"),
            rs.getString("Description"),
            rs.getInt("Price"),
            rs.getInt("SaleNumber"),
            UUID.fromString(rs.getString("CategoryID")),
            rs.getFloat("AverageRating")
    );

    public Product getProduct(String id) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    public void addProduct(Product product) {
        String sql1 = "INSERT INTO product(ProductID, Name, Description, Price, SaleNumber, CategoryID, AverageRating) VALUES (?,?,?,?,?,?,?)";
        String sql = "INSERT INTO ImageOfProduct(productID, imageId) VALUES (?,?)";
        try {
            jdbcTemplate.update(sql1, product.getProductID().toString(), product.getName(), product.getDescription(), product.getCategoryID(), product.getPrice(), product.getSaleNumber(), product.getRating());
            for (int i = 0; i < product.getImages().length; i++) {
                jdbcTemplate.update(sql, product.getProductID().toString(), product.getImages()[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(String id) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String sql0 = "UPDATE Product SET Name = ?, Description = ?, Price = ?, SaleNumber = ?, CategoryID = ?, AverageRating = ? WHERE ProductID = ?";
        try {
            jdbcTemplate.update(sql0, product.getName(), product.getDescription(), product.getPrice(), product.getSaleNumber(), product.getCategoryID().toString(), product.getRating(), product.getProductID().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql1 = "UPDATE ImageOfProduct SET imageId = ? WHERE productID = ?";
        try {
            for (int i = 0; i < product.getImages().length; i++) {
                jdbcTemplate.update(sql1, product.getImages()[i], product.getProductID().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts(List<UUID> productIDs) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Product WHERE ProductID IN (");
        for (int i = 0; i < productIDs.size(); i++) {
            sql.append("?");
            if (i != productIDs.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        try {
            return jdbcTemplate.query(sql.toString(), productRowMapper, productIDs.toArray());
        } catch (Exception e) {
            return null;
        }
    }

    public List<Product> searchProduct(String keywords) {
        List<String> tokens = List.of(keywords.split("\\s+"));
        String sql = generateSearchQuery(tokens);
        try {
            return jdbcTemplate.query(sql, productRowMapper, keywords);
        } catch (Exception e) {
            return null;
        }
    }

    public String generateSearchQuery(List<String> keywords) {
        String selectClause = "SELECT *\n";

        StringBuilder relevanceCases = new StringBuilder();
        for (String keyword : keywords) {
            relevanceCases.append("        (CASE WHEN Name LIKE '%")
                    .append(keyword)
                    .append("%' THEN 1 ELSE 0 END) + ")
                    .append("(CASE WHEN Description LIKE '%")
                    .append(keyword)
                    .append("%' THEN 1 ELSE 0 END) +\n");
        }

        // Remove the last "+" and newline character
        int lastIndex = relevanceCases.lastIndexOf("+");
        if (lastIndex != -1) {
            relevanceCases.delete(lastIndex, relevanceCases.length());
        }

        String fromWhereClause = """
                    ) AS relevance
                FROM
                    Product
                WHERE
                """;

        StringBuilder whereConditions = new StringBuilder();
        for (String keyword : keywords) {
            whereConditions.append("    Name LIKE '%")
                    .append(keyword)
                    .append("%' OR Description LIKE '%")
                    .append(keyword)
                    .append("%' OR\n");
        }

        // Remove the last "OR\n"
        lastIndex = whereConditions.lastIndexOf("OR\n");
        if (lastIndex != -1) {
            whereConditions.delete(lastIndex, whereConditions.length());
        }

        String orderByClause = """
                ORDER BY
                    relevance DESC,
                    Name,
                    Description;
                """;

        return selectClause +
                relevanceCases +
                fromWhereClause +
                whereConditions +
                orderByClause;
    }

    public List<UUID> recommendProduct(String userID) {
        String sql = """
                SELECT *
                FROM Product p JOIN UserCategoryInterest uci ON p.CategoryID = uci.CategoryID
                WHERE uci.UserID = @userID
                ORDER BY uci.InterestPoint DESC, p.Name
                LIMIT 32;
                """;
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> UUID.fromString(rs.getString("ProductID")), userID);
        } catch (Exception e) {
            return null;
        }
    }
}
