package com.aurionpro.service.impl;

import com.aurionpro.service.CaptchaService;
import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Override
    public void verify(String captchaToken) {
        if (captchaToken == null || captchaToken.isBlank()) {
            throw new IllegalArgumentException("CAPTCHA validation failed: token missing");
        }
        // TODO: call Google reCAPTCHA verify API with secret key
    }
}
