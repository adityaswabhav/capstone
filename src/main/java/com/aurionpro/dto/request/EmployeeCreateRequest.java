// File: src/main/java/com/aurionpro/dto/request/EmployeeCreateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeCreateRequest {
  @NotNull private Long organizationId;
  @NotBlank private String code;
  @NotBlank private String fullName;
  @Email @NotBlank private String email;
  @Size(max = 20) private String phone;
  private Long departmentId;
}
