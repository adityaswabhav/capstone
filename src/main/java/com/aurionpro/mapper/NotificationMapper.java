package com.aurionpro.mapper;

import com.aurionpro.dto.response.NotificationResponse;
import com.aurionpro.entity.notify.Notification;

public class NotificationMapper {

    private NotificationMapper() {}

    public static NotificationResponse toResponse(Notification n) {
        if (n == null) return null;
        return NotificationResponse.builder()
                .id(n.getId())
                .userId(n.getUser() != null ? n.getUser().getId() : null)
                .type(n.getType())
                .payloadJson(n.getPayloadJson())
                .readAt(n.getReadAt())
                .build();
    }
}
