// Entity: PaymentRequest
// src/main/java/com/aurionpro/entity/finance/PaymentRequest.java
package com.aurionpro.entity.finance;

import com.aurionpro.entity.base.OrgScopedEntity;
import com.aurionpro.entity.enums.*;
import com.aurionpro.entity.payroll.PayrollCycle;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "payment_requests",
       indexes = { @Index(columnList = "status"), @Index(columnList = "type") })
public class PaymentRequest extends OrgScopedEntity {
  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 16)
  private PaymentRequestType type; // SALARY or VENDOR

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 16)
  private PaymentRequestStatus status = PaymentRequestStatus.SUBMITTED;

  @Enumerated(EnumType.STRING) @Column(nullable = false, length = 8)
  private Currency currency = Currency.INR;

  @PositiveOrZero @Column(nullable = false)
  private Long amount; // in paise

  // SALARY
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "payroll_cycle_id")
  private PayrollCycle payrollCycle;

  // VENDOR
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "business_party_id")
  private BusinessParty businessParty;

  @Column(length = 255)
  private String bankReason;

  @Column(length = 64)
  private String idempotencyKey;
}
