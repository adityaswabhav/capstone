// File: src/main/java/com/aurionpro/dto/response/EmployeeDocumentResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDocResponse {
  private Long id;
  private Long employeeId;
  private Long organizationId;
  private String documentTypeCode;
  private String documentTypeName;
  private String status;
  private String secureUrl;
}
