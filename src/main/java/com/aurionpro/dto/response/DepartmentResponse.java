// File: src/main/java/com/aurionpro/dto/response/DepartmentResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DepartmentResponse {
  private Long id;
  private Long organizationId;
  private String name;
}
