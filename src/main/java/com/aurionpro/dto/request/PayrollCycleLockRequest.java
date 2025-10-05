// File: src/main/java/com/aurionpro/dto/request/PayrollCycleLockRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PayrollCycleLockRequest {
  @NotNull private Long payrollCycleId;
  private String idempotencyKey;
}
