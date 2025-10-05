package com.aurionpro.service;

import com.aurionpro.dto.request.AuthLoginRequest;
import com.aurionpro.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse login(AuthLoginRequest request);
}
