// Entity: SalaryStructure
// src/main/java/com/aurionpro/entity/payroll/SalaryStructure.java
package com.aurionpro.entity.payroll;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "salary_structures")
public class SalaryStructure extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @PositiveOrZero @Column(nullable = false) private Long basic;
  @PositiveOrZero @Column(nullable = false) private Long hra;
  @PositiveOrZero @Column(nullable = false) private Long da;
  @PositiveOrZero @Column(nullable = false) private Long pf;
  @PositiveOrZero @Column(nullable = false) private Long allowances;

  @Column(nullable = false) private LocalDate effectiveFrom;
  private LocalDate effectiveTo; // null => current
}
