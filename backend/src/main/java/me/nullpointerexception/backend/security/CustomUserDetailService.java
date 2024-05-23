package me.nullpointerexception.backend.security;

import me.nullpointerexception.backend.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getUserByUsername(username).orElseThrow();
        return new UserPrincipal(user.getUserID(), user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())
        ));
    }
}
