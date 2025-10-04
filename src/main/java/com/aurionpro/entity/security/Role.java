// Entity: Role
// src/main/java/com/aurionpro/entity/security/Role.java
package com.aurionpro.entity.security;

import com.aurionpro.entity.base.BaseEntity;
import com.aurionpro.entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role extends BaseEntity {
  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 32)
  private RoleName name;
}
