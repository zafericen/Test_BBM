package me.nullpointerexception.backend.model.image;

import java.util.UUID;

public class PostImage extends Image {

    private final UUID postID;

    public PostImage(UUID imageID, UUID postID, byte[] image) {
        super(imageID, image);
        this.postID = postID;
    }

    public PostImage(UUID imageID, UUID postID) {
        super(imageID);
        this.postID = postID;
    }

    public UUID getPostID() {
        return postID;
    }

}
