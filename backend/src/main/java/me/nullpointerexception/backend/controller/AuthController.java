package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.pojo.LoginRequestInfo;
import me.nullpointerexception.backend.pojo.LoginResponse;
import me.nullpointerexception.backend.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public LoginResponse login (@RequestBody LoginRequestInfo loginRequestInfo) {
        return authService.attemptLogin(loginRequestInfo.getUsername(), loginRequestInfo.getPassword());
    }

}
