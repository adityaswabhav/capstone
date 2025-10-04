// Entity: RefDocumentType
// src/main/java/com/aurionpro/entity/ref/RefDocumentType.java
package com.aurionpro.entity.ref;

import com.aurionpro.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "ref_document_type",
       indexes = { @Index(columnList = "active") },
       uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class RefDocumentType extends BaseEntity {
  @NotBlank @Column(nullable = false, length = 64)
  private String code;

  @NotBlank @Column(nullable = false, length = 160)
  private String displayName;

  @Column(length = 255)
  private String allowedMime;

  @Min(1) @Column(nullable = false)
  private Integer maxSizeMb = 5;
}
