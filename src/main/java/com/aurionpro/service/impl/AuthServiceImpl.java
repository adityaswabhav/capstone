package com.aurionpro.service.impl;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.aurionpro.dto.response.TokenResponse;
import com.aurionpro.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final com.aurionpro.security.JwtTokenProvider jwtTokenProvider; // define in your security package
    private final com.aurionpro.security.CaptchaService captchaService;     // optional; implement/replace as needed

    @Override
    public TokenResponse login(com.aurionpro.dto.request.AuthLoginRequest request) {
        captchaService.verify(request.getCaptchaToken());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtTokenProvider.generateToken(auth);
        long expires = jwtTokenProvider.getExpiryMillis();
        return TokenResponse.builder().accessToken(token).expiresInMs(expires).tokenType("Bearer").build();
    }
}
