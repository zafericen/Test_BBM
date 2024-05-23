package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.comment.ProductComment;
import me.nullpointerexception.backend.pojo.DeleteCommentInfo;
import me.nullpointerexception.backend.pojo.ProductCommentInfo;
import me.nullpointerexception.backend.pojo.UpdateProductCommentInfo;
import me.nullpointerexception.backend.repository.ProductCommentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/product/{id}/comments")
@RestController
public class ProductCommentController {

    ProductCommentRepository productCommentRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addComment(@PathVariable String id, @RequestBody ProductCommentInfo productCommentInfo) {
        ProductComment productComment = new ProductComment(UUID.randomUUID(), UUID.fromString(productCommentInfo.userID()), UUID.fromString(id), System.currentTimeMillis(), productCommentInfo.comment(), productCommentInfo.rating());
        try {
            productCommentRepository.addComment(productComment);
            return new ResponseEntity<>("Comment added", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to add comment.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getComments(@PathVariable String id) {
        try {
            List<ProductComment> productComments = productCommentRepository.getComments(id);
            return new ResponseEntity<>(productComments, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Object> deleteComment(@PathVariable String id, @RequestBody DeleteCommentInfo deleteCommentInfo) {
        try {
            productCommentRepository.removeComment(deleteCommentInfo.commentID());
            return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to delete comment.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> updateComment(@PathVariable String id, @RequestBody UpdateProductCommentInfo updateProductCommentInfo) {
        ProductComment productComment = new ProductComment(UUID.fromString(updateProductCommentInfo.commentID()), UUID.fromString(updateProductCommentInfo.userID()), UUID.fromString(id), System.currentTimeMillis(), updateProductCommentInfo.comment(), updateProductCommentInfo.rating());
        try {
            productCommentRepository.updateComment(productComment);
            return new ResponseEntity<>("Comment updated", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Failed to update comment.", HttpStatus.BAD_REQUEST);
        }
    }
}
