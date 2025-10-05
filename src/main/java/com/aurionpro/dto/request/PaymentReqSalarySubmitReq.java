// File: src/main/java/com/aurionpro/dto/request/PaymentRequestSalarySubmitRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import com.aurionpro.entity.enums.Currency;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentReqSalarySubmitReq {
  @NotNull private Long organizationId;
  @NotNull private Long payrollCycleId;
  @NotNull @Positive private Long amount;  // server can recompute
  @NotNull private Currency currency;
  private String idempotencyKey;
}
