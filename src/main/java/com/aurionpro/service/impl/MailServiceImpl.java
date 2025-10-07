package com.aurionpro.service.impl;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.aurionpro.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendPlainText(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }

    @Override
    public void sendTemplate(String to, String subject, String templateName, Map<String, Object> model) {
        // TODO: load template by name (FreeMarker/Thymeleaf). For now, naive replace:
        String body = "Template: " + templateName + "\n";
        for (Map.Entry<String, Object> e : model.entrySet()) {
            body += "${" + e.getKey() + "}=" + String.valueOf(e.getValue()) + "\n";
        }
        sendPlainText(to, subject, body);
    }
}
