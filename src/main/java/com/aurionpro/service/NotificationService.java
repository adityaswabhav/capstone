package com.aurionpro.service;

import com.aurionpro.entity.notify.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> findByUser(Long userId);
}
