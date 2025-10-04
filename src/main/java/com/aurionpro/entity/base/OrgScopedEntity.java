// Entity: OrgScopedEntity
// src/main/java/com/aurionpro/entity/base/OrgScopedEntity.java
package com.aurionpro.entity.base;

import com.aurionpro.entity.org.Organization;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@MappedSuperclass
public abstract class OrgScopedEntity extends BaseEntity {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id", nullable = false)
  private Organization organization;
}
