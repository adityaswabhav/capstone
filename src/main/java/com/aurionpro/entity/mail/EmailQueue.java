// Entity: EmailQueue
// src/main/java/com/aurionpro/entity/mail/EmailQueue.java
package com.aurionpro.entity.mail;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "email_queue", indexes = @Index(columnList = "status"))
public class EmailQueue extends BaseEntity {
  @Email @NotBlank @Column(nullable = false, length = 160)
  private String toEmail;

  @NotBlank @Column(nullable = false, length = 160)
  private String subject;

  @NotBlank @Lob
  private String bodyHtml;

  @NotBlank @Column(nullable = false, length = 16)
  private String status = "PENDING"; // PENDING, SENT, FAILED

  @Column(nullable = false)
  private int attempts = 0;

  @Column(length = 64)
  private String messageId;
}
