// File: src/main/java/com/aurionpro/dto/response/PaymentRequestResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentRequestResponse {
  private Long id;
  private Long organizationId;
  private String type;
  private String status;
  private String currency;
  private Long amount;
  private Long payrollCycleId;
  private Long businessPartyId;
  private String bankReason;
}
