// Entity: BaseEntity
// src/main/java/com/aurionpro/entity/base/BaseEntity.java
package com.aurionpro.entity.base;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate @Column(nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate @Column(nullable = false)
  private Instant updatedAt;

  @CreatedBy @Column(updatable = false, length = 160)
  private String createdBy;

  @LastModifiedBy @Column(length = 160)
  private String updatedBy;

  @Column(nullable = false)
  private Boolean active = true;
}
