// Entity: OrgDocument
// src/main/java/com/aurionpro/entity/org/OrgDocument.java
package com.aurionpro.entity.org;

import com.aurionpro.entity.base.BaseEntity;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.ref.RefDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "org_documents",
       indexes = { @Index(columnList = "status"), @Index(columnList = "organization_id") })
public class OrgDocument extends BaseEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id", nullable = false)
  private Organization organization;

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
