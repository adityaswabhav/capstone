// File: src/main/java/com/aurionpro/dto/response/OrganizationResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrganizationResponse {
  private Long id;
  private String name;
  private String status;
  private String rejectionReason;
  private String contactEmail;
  private String contactPhone;
}
