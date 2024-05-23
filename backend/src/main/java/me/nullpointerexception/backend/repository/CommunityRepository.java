package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.community.Community;
import me.nullpointerexception.backend.model.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.UUID;

@Repository
public class CommunityRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Community> communityRowMapper = (rs, rowNum) -> new Community(
                UUID.fromString(rs.getString("communityID")),
                UUID.fromString(rs.getString("categoryID"))
    );

    public Community getCommunity(String id){
        try {
            String sql = "SELECT * FROM Community where communityID = ?";
            return jdbcTemplate.queryForObject(sql, communityRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    public Community getCommunityForCategory(UUID categoryID){
        try {
            String sql = "SELECT * FROM Community where categoryID = ?";
            return jdbcTemplate.queryForObject(sql, communityRowMapper, categoryID);
        } catch (Exception e) {
            return null;
        }
    }
}
