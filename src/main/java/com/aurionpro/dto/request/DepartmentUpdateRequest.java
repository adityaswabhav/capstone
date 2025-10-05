// File: src/main/java/com/aurionpro/dto/request/DepartmentUpdateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DepartmentUpdateRequest {
  @NotNull private Long id;
  @NotNull private Long organizationId;
  @NotBlank private String name;
}
