package me.nullpointerexception.backend.service;

import me.nullpointerexception.backend.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<UserEntity> userEntityRowMapper = (rs, rowNum) -> new UserEntity(
            rs.getString("UserID"),
            rs.getString("Username"),
            rs.getString("Password"),
            rs.getString("UserRole")
    );

    public Optional<UserEntity> getUserByUsername(String username) {
        String sql = "SELECT * FROM User WHERE Username = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{username}, userEntityRowMapper));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
