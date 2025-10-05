// File: src/main/java/com/aurionpro/dto/request/SalaryStructureUpsertRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalaryStructureUpsertRequest {
  @NotNull private Long organizationId;
  @NotNull private Long employeeId;
  @PositiveOrZero private Long basic;
  @PositiveOrZero private Long hra;
  @PositiveOrZero private Long da;
  @PositiveOrZero private Long pf;
  @PositiveOrZero private Long allowances;
  @NotNull private LocalDate effectiveFrom;
}
