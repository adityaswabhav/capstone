// File: src/main/java/com/aurionpro/dto/request/ReportFilterRequest.java
package com.aurionpro.dto.request;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReportFilterRequest {
  private LocalDate startDate;
  private LocalDate endDate;
  private String frequency;   // MONTHLY | QUARTERLY | YEARLY
  private String format;      // PDF | EXCEL
  private String employeeId;
  private String vendorName;
  private String paymentId;
  private String department;
}
