// Entity: ConcernAttachment
// src/main/java/com/aurionpro/entity/support/ConcernAttachment.java
package com.aurionpro.entity.support;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.ref.RefDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "concern_attachments")
public class ConcernAttachment extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "concern_id", nullable = false)
  private Concern concern;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "document_type_id", nullable = false)
  private RefDocumentType documentType;

  @NotBlank @Column(nullable = false, length = 180)
  private String cloudinaryPublicId;

  @NotBlank @Column(nullable = false, length = 300)
  private String secureUrl;

  @Positive
  private Long sizeBytes;

  @Column(length = 80)
  private String contentType;
}
