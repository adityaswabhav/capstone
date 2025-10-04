// Entity: User
// src/main/java/com/aurionpro/entity/security/User.java
package com.aurionpro.entity.security;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users", indexes = { @Index(columnList = "email", unique = true) })
public class User extends BaseEntity {
  @Email @NotBlank @Column(nullable = false, unique = true, length = 160)
  private String email;

  @NotBlank @Column(nullable = false, length = 120)
  private String passwordHash;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @Column(length = 120)
  private String fullName;

  @Column(length = 20)
  private String phone;
}
