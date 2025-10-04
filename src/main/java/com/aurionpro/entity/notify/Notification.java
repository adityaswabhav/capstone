// Entity: Notification
// src/main/java/com/aurionpro/entity/notify/Notification.java
package com.aurionpro.entity.notify;

import com.aurionpro.entity.base.BaseEntity;
import com.aurionpro.entity.security.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "notifications", indexes = @Index(columnList = "readAt"))
public class Notification extends BaseEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false, length = 64)
  private String type; // e.g., ORG_APPROVED, PAYROLL_APPROVED

  @Lob @Column(nullable = false)
  private String payloadJson;

  private Instant readAt;
}
