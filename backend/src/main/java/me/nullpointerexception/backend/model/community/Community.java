package me.nullpointerexception.backend.model.community;

import java.util.UUID;

public class Community {

    private final UUID communityID;
    private final UUID categoryID;

    public Community(UUID communityID, UUID categoryID) {
        this.communityID = communityID;
        this.categoryID = categoryID;
    }

    public UUID getCommunityID() {
        return communityID;
    }

    public UUID getCategoryID() {
        return categoryID;
    }
}
