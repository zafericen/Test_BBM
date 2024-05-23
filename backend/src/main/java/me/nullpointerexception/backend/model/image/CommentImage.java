package me.nullpointerexception.backend.model.image;

import java.util.UUID;

public class CommentImage extends Image {
        private final UUID commentID;

    public CommentImage(UUID imageID, UUID commentID, byte[] image) {
        super(imageID, image);
        this.commentID = commentID;
    }

    public CommentImage(UUID imageID, UUID commentID) {
        super(imageID);
        this.commentID = commentID;
    }

    public UUID getCommentID() {
        return commentID;
    }
}
