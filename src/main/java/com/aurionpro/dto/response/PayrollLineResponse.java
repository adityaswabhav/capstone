// File: src/main/java/com/aurionpro/dto/response/PayrollLineResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PayrollLineResponse {
  private Long id;
  private Long organizationId;
  private Long payrollCycleId;
  private Long employeeId;
  private String employeeCode;
  private String employeeName;
  private Long earningsTotal;
  private Long deductionsTotal;
  private Long net;
  private String remarks;
}
