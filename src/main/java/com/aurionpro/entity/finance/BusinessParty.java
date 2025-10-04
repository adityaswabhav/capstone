// Entity: BusinessParty
// src/main/java/com/aurionpro/entity/finance/BusinessParty.java
package com.aurionpro.entity.finance;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.ref.RefBusinessPartyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "business_parties",
       uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id","name","business_party_type_id"}))
public class BusinessParty extends OrgScopedEntity {
  @NotBlank @Column(nullable = false, length = 160)
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "business_party_type_id", nullable = false)
  private RefBusinessPartyType type;

  @Email @Column(length = 160)
  private String contactEmail;

  @Column(length = 20)
  private String contactPhone;

  @Column(length = 32)
  private String ifsc;

  @Column(length = 34)
  private String accountMasked;
}
