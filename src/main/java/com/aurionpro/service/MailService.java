package com.aurionpro.service;

import java.util.Map;

public interface MailService {
    void sendPlainText(String to, String subject, String body);
    void sendTemplate(String to, String subject, String templateName, Map<String, Object> model);
}
