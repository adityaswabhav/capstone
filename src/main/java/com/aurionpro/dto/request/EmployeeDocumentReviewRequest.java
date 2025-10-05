// File: src/main/java/com/aurionpro/dto/request/EmployeeDocumentReviewRequest.java
package com.aurionpro.dto.request;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDocumentReviewRequest {
  private boolean approve;
  private String reasonCode;  // optional
  private String note;
}
