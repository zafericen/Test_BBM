package me.nullpointerexception.backend.repository;

import me.nullpointerexception.backend.model.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostRepository {
    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<Post> postRowMapper = (rs, rowNum) -> new Post(
            UUID.fromString(rs.getString("userID")),
            UUID.fromString(rs.getString("communityID")),
            UUID.fromString(rs.getString("postID")),
            rs.getString("title"),
            rs.getString("description"),
            rs.getLong("postDate")
    );

    public void addPost(Post post){
        String sql1 = "INSERT INTO post(userID, communityID, postID, title, description, postDate) VALUES (?,?,?,?,?,?)";
        try{
            jdbcTemplate.update(sql1,post.getUserID(),post.getCommunityID(),post.getPostID(),post.getTitle(),post.getDescription(),post.getPostDate());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Post getPost(UUID postID){
        String sql = "SELECT * FROM post WHERE postID = ?";
        try{
            return jdbcTemplate.queryForObject(sql,postRowMapper,postID);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Post> getPosts(UUID communityID){
        String sql = "SELECT * FROM post WHERE communityID = ?";
        try{
            return jdbcTemplate.query(sql,postRowMapper,communityID);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void deletePost(UUID postID){
        String sql = "DELETE FROM post WHERE postID = ?";
        try{
            jdbcTemplate.update(sql,postID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePost(Post post){
        String sql = "UPDATE post SET title = ?, description = ? WHERE postID = ?";
        try{
            jdbcTemplate.update(sql,post.getTitle(),post.getDescription(),post.getPostID());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
