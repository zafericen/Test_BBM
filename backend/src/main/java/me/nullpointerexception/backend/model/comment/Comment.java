package me.nullpointerexception.backend.model.comment;

import java.util.UUID;

public class Comment {
    protected final UUID commentID;
    protected final UUID userID;
    protected final long commentDate;
    protected final String comment;
    protected final byte[][] images;

    public Comment(UUID commentID, UUID userID, String comment, long commentDate, byte[][] images) {
        this.commentID = commentID;
        this.userID = userID;
        this.comment = comment;
        this.commentDate = commentDate;
        this.images = images;
    }

    public Comment(UUID commentID, UUID userID, String comment, long commentDate) {
        this.commentID = commentID;
        this.userID = userID;
        this.comment = comment;
        this.commentDate = commentDate;
        this.images = new byte[5][];
    }

    public Comment(UUID userID, String comment) {
        this.commentID = UUID.randomUUID();
        this.userID = userID;
        this.comment = comment;
        this.commentDate = System.currentTimeMillis();
        this.images = new byte[5][];
    }

    public UUID getCommentID() {
        return commentID;
    }

    public UUID getUserID() {
        return userID;
    }

    public long getCommentDate() {
        return commentDate;
    }

    public String getComment() {
        return comment;
    }
}
