// Entity: Department
// src/main/java/com/aurionpro/entity/org/Department.java
package com.aurionpro.entity.org;

import com.aurionpro.entity.base.OrgScopedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "departments",
       uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id","name"}))
public class Department extends OrgScopedEntity {
  @NotBlank @Column(nullable = false, length = 120)
  private String name;
}
