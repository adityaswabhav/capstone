// File: src/main/java/com/aurionpro/dto/response/EmployeeResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeResponse {
  private Long id;
  private Long organizationId;
  private String code;
  private String fullName;
  private String email;
  private String phone;
  private Long departmentId;
  private String departmentName;
  private Boolean inPayroll;
}
