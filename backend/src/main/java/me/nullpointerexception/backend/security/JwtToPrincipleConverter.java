package me.nullpointerexception.backend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipleConverter {

        private List<SimpleGrantedAuthority> convertRoles(DecodedJWT jwt) {
            var claims = jwt.getClaim("roles");
            if (claims == null || claims.isMissing())
                return  List.of();
            return claims.asList(SimpleGrantedAuthority.class);
        }

        public UserPrincipal convert(DecodedJWT jwt) {
            return new UserPrincipal(jwt.getSubject(), jwt.getClaim("username").asString(),
                    jwt.getClaim("password").asString(), convertRoles(jwt));
        }
}
