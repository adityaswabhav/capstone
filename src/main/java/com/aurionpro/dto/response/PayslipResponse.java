// File: src/main/java/com/aurionpro/dto/response/PayslipResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PayslipResponse {
  private Long id;
  private Long organizationId;
  private Long payrollCycleId;
  private Long employeeId;
  private String secureUrl;
}
