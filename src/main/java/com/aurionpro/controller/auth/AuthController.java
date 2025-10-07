package com.aurionpro.controller.auth;

import com.aurionpro.dto.request.AuthLoginRequest;
import com.aurionpro.dto.response.TokenResponse;
import com.aurionpro.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@RequestBody @Valid AuthLoginRequest request) {
        return authService.login(request);
    }
}
