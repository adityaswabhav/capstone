// File: src/main/java/com/aurionpro/dto/request/PaymentRequestVendorCreateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import com.aurionpro.entity.enums.Currency;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentReqVendorCreateReq {
  @NotNull private Long organizationId;
  @NotNull private Long businessPartyId;
  @NotNull @Positive private Long amount;   // in paise
  @NotNull private Currency currency;
  private String idempotencyKey;
}
