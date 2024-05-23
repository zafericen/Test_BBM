package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.post.Post;
import me.nullpointerexception.backend.pojo.*;
import me.nullpointerexception.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/post/{id}")
@RestController
public class PostController {

    PostRepository postRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addPost(@PathVariable String id, @RequestBody PostInfo postInfo){
        Post post = new Post(UUID.fromString(postInfo.userID()), UUID.fromString(postInfo.communityID()), postInfo.title(), postInfo.description(), System.currentTimeMillis());
        try {
            postRepository.addPost(post);
            return new ResponseEntity<>("Post successfully added.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to add the post.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<Object> getPost(@PathVariable String id){
        Post post = postRepository.getPost(UUID.fromString(id));
        if(post != null){
            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Post not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Object> deletePost(@RequestBody DeletePostInfo deletePostInfo){
        try {
            postRepository.deletePost(UUID.fromString(deletePostInfo.postId()));
            return new ResponseEntity<>("Post successfully deleted.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to delete the post.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> updatePost(@RequestBody UpdatePostInfo updatePostInfo){
        Post post = postRepository.getPost(UUID.fromString(updatePostInfo.postID()));
        if(post != null){
            post = new Post(post.getUserID(), post.getCommunityID(), post.getPostID(), updatePostInfo.title(), updatePostInfo.description(), System.currentTimeMillis());
            try {
                postRepository.addPost(post);
                return new ResponseEntity<>("Post successfully updated.", HttpStatus.OK);
            } catch (DataAccessException e) {
                return new ResponseEntity<>("Failed to update the post.", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("Post not found.", HttpStatus.NOT_FOUND);
        }
    }
}
