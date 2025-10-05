// File: src/main/java/com/aurionpro/dto/response/SalaryStructureResponse.java
package com.aurionpro.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalaryStructureResponse {
  private Long id;
  private Long organizationId;
  private Long employeeId;
  private Long basic;
  private Long hra;
  private Long da;
  private Long pf;
  private Long allowances;
  private LocalDate effectiveFrom;
  private LocalDate effectiveTo;
}
