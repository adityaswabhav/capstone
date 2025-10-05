// File: src/main/java/com/aurionpro/dto/request/DepartmentCreateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DepartmentCreateRequest {
  @NotNull private Long organizationId;
  @NotBlank private String name;
}
