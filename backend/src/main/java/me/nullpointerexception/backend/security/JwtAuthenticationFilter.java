package me.nullpointerexception.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipleConverter jwtToPrincipleConverter;

    public JwtAuthenticationFilter(JwtDecoder jwtDecoder, JwtToPrincipleConverter jwtToPrincipleConverter) {
        this.jwtDecoder = jwtDecoder;
        this.jwtToPrincipleConverter = jwtToPrincipleConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extractToken(request)
                .map(jwtDecoder::decode)
                .map(jwtToPrincipleConverter::convert)
                .map(UserPrincipleAuthenticationToken::new)
                .ifPresent(authentication -> {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token) || !token.startsWith("Bearer "))
            return Optional.empty();
        return Optional.of(token.substring(7));
    }
}
