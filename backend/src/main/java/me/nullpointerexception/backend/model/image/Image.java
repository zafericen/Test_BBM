package me.nullpointerexception.backend.model.image;

import java.util.UUID;

public class Image {

    protected final UUID imageID;
    protected byte[] image;

    public Image(UUID imageID, byte[] image) {
        this.imageID = imageID;
        this.image = image;
    }

    public Image(UUID imageID) {
        this.imageID = imageID;
        this.image = null;
    }

    public UUID getImageID() {
        return imageID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
