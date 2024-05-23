package me.nullpointerexception.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserEntity {
    private String userID;

    private String username;

    @JsonIgnore
    private String password;

    private boolean enabled;

    private String role;

    public UserEntity(String userID, String username, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
