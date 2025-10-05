// File: src/main/java/com/aurionpro/dto/request/OrgApprovalRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrgApprovalRequest {
  private boolean approve;
  private String reasonCode;             // optional
  @NotBlank(message = "Decision note is required if rejecting")
  private String note;
}
