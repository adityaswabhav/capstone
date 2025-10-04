// Entity: PayrollCycle
// src/main/java/com/aurionpro/entity/payroll/PayrollCycle.java
package com.aurionpro.entity.payroll;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.enums.PayrollStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "payroll_cycles",
       uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id","periodMonth","periodYear"}))
public class PayrollCycle extends OrgScopedEntity {
  @Min(1) @Max(12) @Column(nullable = false)
  private int periodMonth;

  @Min(2000) @Max(2100) @Column(nullable = false)
  private int periodYear;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 24)
  private PayrollStatus status = PayrollStatus.DRAFT;

  @PositiveOrZero
  private Long totalNet;
}
