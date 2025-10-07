package com.aurionpro.controller.notify;

import com.aurionpro.dto.response.NotificationResponse;
import com.aurionpro.entity.notify.Notification;
import com.aurionpro.mapper.NotificationMapper;
import com.aurionpro.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> list(@RequestParam Long userId) {
        List<Notification> list = notificationService.findByUser(userId);
        return list.stream().map(NotificationMapper::toResponse).toList();
    }
}
