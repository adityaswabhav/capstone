// Entity: Bank
// src/main/java/com/aurionpro/entity/bank/Bank.java
package com.aurionpro.entity.bank;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "bank", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Bank extends BaseEntity {
  @NotBlank @Column(nullable = false, unique = true, length = 32)
  private String code;

  @NotBlank @Column(nullable = false, length = 165)
  private String name;

  @Email @Column(length = 255)
  private String supportEmail;
}
