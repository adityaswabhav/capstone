// File: src/main/java/com/aurionpro/dto/request/EmployeeUpdateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeUpdateRequest {
  @NotNull private Long id;
  @NotNull private Long organizationId;
  @NotBlank private String fullName;
  @Email private String email;
  @Size(max = 20) private String phone;
  private Long departmentId;
  private Boolean inPayroll;
}
