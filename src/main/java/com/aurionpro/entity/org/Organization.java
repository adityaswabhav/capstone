// Entity: Organization
// src/main/java/com/aurionpro/entity/org/Organization.java
package com.aurionpro.entity.org;

import com.aurionpro.entity.bank.Bank;
import com.aurionpro.entity.base.BaseEntity;
import com.aurionpro.entity.enums.OrganizationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "organizations",
       indexes = { @Index(columnList = "status"), @Index(columnList = "name") })
public class Organization extends BaseEntity {
  @NotBlank @Column(nullable = false, length = 160)
  private String name;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 24)
  private OrganizationStatus status = OrganizationStatus.PENDING;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "bank_id", nullable = false)
  private Bank bank;

  @Column(length = 255)
  private String rejectionReason;

  @Email @Column(length = 160)
  private String contactEmail;

  @Column(length = 20)
  private String contactPhone;
}
