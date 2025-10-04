// Entity: EmployeeDocument
// src/main/java/com/aurionpro/entity/employee/EmployeeDocument.java
package com.aurionpro.entity.employee;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.ref.RefDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employee_documents", indexes = @Index(columnList = "status"))
public class EmployeeDocument extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "document_type_id", nullable = false)
  private RefDocumentType documentType;

  @NotBlank @Column(nullable = false, length = 180)
  private String cloudinaryPublicId;

  @NotBlank @Column(nullable = false, length = 300)
  private String secureUrl;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 24)
  private DocumentStatus status = DocumentStatus.PENDING;

  @Column(length = 255)
  private String notes;

  @Positive
  private Long sizeBytes;

  @Column(length = 80)
  private String contentType;
}
