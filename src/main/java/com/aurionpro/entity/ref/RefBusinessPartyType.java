// Entity: RefBusinessPartyType
// src/main/java/com/aurionpro/entity/ref/RefBusinessPartyType.java
package com.aurionpro.entity.ref;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "ref_business_party_type",
       indexes = { @Index(columnList = "active") },
       uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class RefBusinessPartyType extends BaseEntity {
  @NotBlank @Column(nullable = false, length = 64)
  private String code;

  @NotBlank @Column(nullable = false, length = 160)
  private String displayName;

  @Column(length = 255)
  private String description;
}
