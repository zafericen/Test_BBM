package me.nullpointerexception.backend.model.post;

import java.util.UUID;

public class Post {

    private final UUID userID;
    private final UUID communityID;
    private final UUID postID;
    private final String title;
    private final String description;
    private final long postDate;
    private final byte[][] images;

    public Post(UUID userID, UUID communityID, UUID postID, String title, String description, long postDate, byte[][] images) {
        this.userID = userID;
        this.communityID = communityID;
        this.postID = postID;
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.images = images;
    }

    public Post(UUID userID, UUID communityID, UUID postID, String title, String description, long postDate) {
        this.userID = userID;
        this.communityID = communityID;
        this.postID = postID;
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.images = new byte[5][];
    }

    public Post(UUID userID, UUID communityID, String title, String description, long postDate) {
        this.userID = userID;
        this.communityID = communityID;
        this.postID = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.images = new byte[5][];
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getCommunityID() {
        return communityID;
    }

    public UUID getPostID() {
        return postID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getPostDate() {
        return postDate;
    }

    public byte[][] getImages() {
        return images;
    }

    public byte[] getImage(int index) {
        return images[index];
    }

    public void setImage(int index, byte[] image) {
        this.images[index] = image;
    }
    public void removeImage(int index) {
        this.images[index] = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postID.equals(post.postID);
    }
}
