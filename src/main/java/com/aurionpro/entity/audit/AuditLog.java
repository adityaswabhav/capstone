// Entity: AuditLog
// src/main/java/com/aurionpro/entity/audit/AuditLog.java
package com.aurionpro.entity.audit;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "audit_logs",
       indexes = { @Index(columnList = "entityType,entityId") })
public class AuditLog extends BaseEntity {
  @Column(nullable = false, length = 64)
  private String entityType; // e.g., "Organization"

  @Column(nullable = false)
  private Long entityId;

  @Column(nullable = false, length = 64)
  private String action; // e.g., "APPROVE", "REJECT", "CREATE"

  @Lob private String oldValuesJson;
  @Lob private String newValuesJson;
}
