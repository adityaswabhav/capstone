// File: src/main/java/com/aurionpro/dto/response/PayrollCycleResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PayrollCycleResponse {
  private Long id;
  private Long organizationId;
  private int periodMonth;
  private int periodYear;
  private String status;
  private Long totalNet;
}
