// Entity: RefReasonCode
// src/main/java/com/aurionpro/entity/ref/RefReasonCode.java
package com.aurionpro.entity.ref;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "ref_reason_code",
       indexes = { @Index(columnList = "active"), @Index(columnList = "scope") },
       uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class RefReasonCode extends BaseEntity {
  @NotBlank @Column(nullable = false, length = 64)
  private String code;

  @NotBlank @Column(nullable = false, length = 160)
  private String displayName;

  @NotBlank @Column(nullable = false, length = 64)
  private String scope; // e.g., ORG_APPROVAL, BANK_DECISION, EMP_DOC_REJECT

  @Column(length = 255)
  private String description;
}
