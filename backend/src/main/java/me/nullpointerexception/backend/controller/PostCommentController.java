package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.comment.PostComment;
import me.nullpointerexception.backend.pojo.DeleteCommentInfo;
import me.nullpointerexception.backend.pojo.PostCommentInfo;
import me.nullpointerexception.backend.pojo.UpdatePostCommentInfo;
import me.nullpointerexception.backend.repository.PostCommentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/post/{id}/comments")
@RestController
public class PostCommentController {

    PostCommentRepository postCommentRepository;

    public PostCommentController(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addComment(@PathVariable String id, @RequestBody PostCommentInfo postCommentInfo) {
        PostComment postComment = new PostComment(UUID.randomUUID(), UUID.fromString(postCommentInfo.userID()), UUID.fromString(id), System.currentTimeMillis(), postCommentInfo.comment());
        try {
            postCommentRepository.addComment(postComment);
            return ResponseEntity.ok("Comment added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add comment.");
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getComments(@PathVariable String id) {
        try {
            List<PostComment> postComments = postCommentRepository.getComments(id);
            return new ResponseEntity<>(postComments, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Object> deleteComment(@PathVariable String id, @RequestBody DeleteCommentInfo deleteCommentInfo) {
        try {
            postCommentRepository.removeComment(deleteCommentInfo.commentID());
            return ResponseEntity.ok("Comment deleted");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("Failed to delete comment.");
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> updateComment(@PathVariable String id, @RequestBody UpdatePostCommentInfo updatePostCommentInfo) {
        PostComment postComment = new PostComment(UUID.fromString(updatePostCommentInfo.commentID()), UUID.fromString(updatePostCommentInfo.userID()), UUID.fromString(id), System.currentTimeMillis(), updatePostCommentInfo.comment());
        try {
            postCommentRepository.updateComment(postComment);
            return ResponseEntity.ok("Comment updated");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("Failed to update comment.");
        }
    }
}
