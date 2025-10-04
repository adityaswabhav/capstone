// Entity: BankAccountUpdateRequest
// src/main/java/com/aurionpro/entity/employee/BankAccountUpdateRequest.java
package com.aurionpro.entity.employee;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.enums.DocumentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "bank_account_update_requests")
public class BankAccountUpdateRequest extends OrgScopedEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @NotBlank @Column(nullable = false, length = 34)
  private String newAccountMasked;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "proof_document_id", unique = true)
  private EmployeeDocument proofDocument;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 24)
  private DocumentStatus status = DocumentStatus.PENDING;

  @Column(length = 255)
  private String reviewerNotes;
}
