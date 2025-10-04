// Entity: Payslip
// src/main/java/com/aurionpro/entity/payroll/Payslip.java
package com.aurionpro.entity.payroll;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "payslips",
       uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id","employee_id","payroll_cycle_id"}))
public class Payslip extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "payroll_cycle_id", nullable = false)
  private PayrollCycle payrollCycle;

  @Column(nullable = false, length = 180)
  private String cloudinaryPublicId;

  @Column(nullable = false, length = 300)
  private String secureUrl;
}
