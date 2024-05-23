package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.image.CommentImage;
import me.nullpointerexception.backend.pojo.CommentImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ImageController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<CommentImage> commentImageRowMapper = (rs, rowNum) -> new CommentImage(
            UUID.fromString(rs.getString("ImageID")),
            UUID.fromString(rs.getString("CommentID")),
            rs.getBytes("Image")
    );

    @GetMapping(value = "/CommentImage/{commentID}")
    public List<CommentImage> getCommentImages(@PathVariable String commentID)
    {
        String sql = "SELECT * FROM CommentImage WHERE CommnetID = ?;";
        List<CommentImage> images = jdbcTemplate.query(sql,commentImageRowMapper,commentID);
        return images;
    }

    @PostMapping(value = "/addCommentImage")
    public ResponseEntity<Object> getCommentImages(CommentImageInfo commentImageInfo)
    {
        String sql = "INSERT INTO CommentImage(CommentID,Image,ImageID) VALUES (?,?,?); ";
        jdbcTemplate.update(sql,commentImageInfo.CommentID(),commentImageInfo.Image(), UUID.randomUUID().toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
