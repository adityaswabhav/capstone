// File: src/main/java/com/aurionpro/dto/response/NotificationResponse.java
package com.aurionpro.dto.response;

import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationResponse {
  private Long id;
  private Long userId;
  private String type;
  private String payloadJson;
  private Instant readAt;
}
