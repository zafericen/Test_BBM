package me.nullpointerexception.backend.service;

import me.nullpointerexception.backend.pojo.LoginResponse;
import me.nullpointerexception.backend.security.JwtIssuer;
import me.nullpointerexception.backend.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    public AuthService(JwtIssuer jwtIssuer, AuthenticationManager authenticationManager) {
        this.jwtIssuer = jwtIssuer;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse attemptLogin(String username, String password) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var token = jwtIssuer.issueToken(principal.getUserID(), principal.getUsername(), principal.getAuthorities());
        return new LoginResponse(token);
    }
}

