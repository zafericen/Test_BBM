package me.nullpointerexception.backend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final String userID;
    private final String username;

    @JsonIgnore
    private final String password;

    private final List<? extends GrantedAuthority> authorities;

    public UserPrincipal(String userID, String username, String password, List<? extends GrantedAuthority> roles) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.authorities = List.copyOf((Collection<? extends GrantedAuthority>) roles);
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
