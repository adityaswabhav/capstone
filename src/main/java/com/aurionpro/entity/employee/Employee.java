// Entity: Employee
// src/main/java/com/aurionpro/entity/employee/Employee.java
package com.aurionpro.entity.employee;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.org.Department;
import com.aurionpro.entity.security.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employees", indexes = @Index(columnList = "inPayroll"))
public class Employee extends OrgScopedEntity {
  @NotBlank @Column(nullable = false, length = 64)
  private String code; // unique within org (enforce in DB via unique + org_id if desired)

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @Column(length = 20)
  private String bankAccountLast4;

  @Column(nullable = false)
  private Boolean inPayroll = false;
}
