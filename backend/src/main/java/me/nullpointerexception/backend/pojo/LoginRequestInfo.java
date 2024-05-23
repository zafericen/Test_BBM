package me.nullpointerexception.backend.pojo;

public record LoginRequestInfo(String username, String password) {
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
