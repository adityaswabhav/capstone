package com.aurionpro.service.impl;

import com.aurionpro.entity.notify.Notification;
import com.aurionpro.repository.notify.NotificationRepository;
import com.aurionpro.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements com.aurionpro.service.NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> findByUser(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
