// Entity: PayrollLine
// src/main/java/com/aurionpro/entity/payroll/PayrollLine.java
package com.aurionpro.entity.payroll;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "payroll_lines",
       uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id","payroll_cycle_id","employee_id"}))
public class PayrollLine extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "payroll_cycle_id", nullable = false)
  private PayrollCycle payrollCycle;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @PositiveOrZero @Column(nullable = false) private Long earningsTotal;
  @PositiveOrZero @Column(nullable = false) private Long deductionsTotal;
  @PositiveOrZero @Column(nullable = false) private Long net;

  @Column(length = 255) private String remarks;
}
