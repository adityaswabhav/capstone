package com.aurionpro.security;

import java.io.IOException;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var body = Map.of(
                "status", 401,
                "error", "Unauthorized",
                "message", authException.getMessage(),
                "path", request.getRequestURI()
        );
        MAPPER.writeValue(response.getOutputStream(), body);
    }
}
