package com.aurionpro.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.aurionpro.dto.request.AuthLoginRequest;
import com.aurionpro.dto.response.TokenResponse;
import com.aurionpro.service.AuthService;
import com.aurionpro.service.CaptchaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final com.aurionpro.security.JwtTokenProvider jwtTokenProvider; // TODO: provide in security config
    private final CaptchaService captchaService;

    @Override
    public TokenResponse login(AuthLoginRequest request) {
        captchaService.verify(request.getCaptchaToken());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtTokenProvider.generateToken(auth);
        long expires = jwtTokenProvider.getExpiryMillis();
        return TokenResponse.builder().accessToken(token).expiresInMs(expires).tokenType("Bearer").build();
    }
}
