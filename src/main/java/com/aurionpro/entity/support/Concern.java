// Entity: Concern
// src/main/java/com/aurionpro/entity/support/Concern.java
package com.aurionpro.entity.support;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.enums.ConcernStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "concerns", indexes = @Index(columnList = "status"))
public class Concern extends OrgScopedEntity {
	
	@OneToMany(mappedBy="concern", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ConcernAttachment> attachments = new ArrayList<>();

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @NotBlank @Column(nullable = false, length = 160)
  private String subject;

  @NotBlank @Column(nullable = false, length = 2000)
  private String description;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 16)
  private ConcernStatus status = ConcernStatus.OPEN;

  @Column(length = 255)
  private String resolutionNote;
}
