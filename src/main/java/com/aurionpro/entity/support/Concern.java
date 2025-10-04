// Entity: Concern
// src/main/java/com/aurionpro/entity/support/Concern.java
package com.aurionpro.entity.support;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.enums.ConcernStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "concerns", indexes = @Index(columnList = "status"))
public class Concern extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @NotBlank @Column(nullable = false, length = 160)
  private String subject;

  @NotBlank @Column(nullable = false, length = 2000)
  private String description;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 16)
  private ConcernStatus status = ConcernStatus.OPEN;

  @Column(length = 255)
  private String resolutionNote;
}
