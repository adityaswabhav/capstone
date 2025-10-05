// File: src/main/java/com/aurionpro/dto/request/PayrollCycleCreateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PayrollCycleCreateRequest {
  @NotNull private Long organizationId;
  @Min(1) @Max(12) private int periodMonth;
  @Min(2000) @Max(2100) private int periodYear;
}
