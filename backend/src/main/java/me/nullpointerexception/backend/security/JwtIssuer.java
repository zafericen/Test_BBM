package me.nullpointerexception.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Component
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    public JwtIssuer(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String issueToken(String userID, String username, Collection<? extends GrantedAuthority> roles) {
        return JWT.create()
                .withSubject(userID)
                .withExpiresAt(Instant.now().plus(Duration.ofDays(1)))
                .withClaim("username", username)
                .withClaim("roles", roles.stream().map(GrantedAuthority::getAuthority).toList())
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
