package me.nullpointerexception.backend.model.user;

import java.util.UUID;

public class User {
    private final UUID userID;
    private final String userName;
    private final String email;
    private final byte[] userPassword;
    private final long registerDate;
    private final long lastOnline;
    private final UserStatus userStatus;
    private final byte[] profilePicture;
    private final UserType userType;

    /**
     * Constructor for already existing users in the database
     */
    public User(UUID userID, String userName, String email, byte[] UserPassword, long registerDate, long lastOnline, UserStatus userStatus, byte[] profilePicture, UserType userType) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.userPassword = UserPassword;
        this.registerDate = registerDate;
        this.lastOnline = lastOnline;
        this.userStatus = userStatus;
        this.profilePicture = profilePicture;
        this.userType = userType;
    }

    /**
     * Constructor for new users
     */
    public User(String username, String email, byte[] password, UserType type) {
        this(UUID.randomUUID(), username, email, password, System.currentTimeMillis(), System.currentTimeMillis(), UserStatus.ACTIVE, null, type);
    }

    public User() {
        this(UUID.randomUUID(), "", "", null, System.currentTimeMillis(), System.currentTimeMillis(), UserStatus.ACTIVE, null, UserType.USER);
    }

    public UUID getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public byte[] getUserPassword() {
        return userPassword;
    }
    public long getRegisterDate() {
        return registerDate;
    }
    public long getLastOnline() {
        return lastOnline;
    }
    public UserStatus getUserStatus() {
        return userStatus;
    }
    public byte[] getProfilePicture() {
        return profilePicture;
    }
    public UserType getUserType() {
        return userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.userID.equals(user.userID);
    }
}
