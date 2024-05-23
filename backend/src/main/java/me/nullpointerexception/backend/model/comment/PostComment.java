package me.nullpointerexception.backend.model.comment;

import java.util.UUID;

public class PostComment extends Comment {
    private final UUID postID;

    public PostComment(UUID commentID, UUID userID, UUID postID, long commentDate, String comment, byte[][] images) {
        super(commentID, userID, comment, commentDate, images);
        this.postID = postID;
    }

    public PostComment(UUID commentID, UUID userID, UUID postID, long commentDate, String comment) {
        super(commentID, userID, comment, commentDate);
        this.postID = postID;
    }

    public PostComment(String comment, UUID userID, UUID postID) {
        super(userID, comment);
        this.postID = postID;
    }

    public UUID getPostID() {
        return postID;
    }
}
