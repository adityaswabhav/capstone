// File: src/main/java/com/aurionpro/dto/request/EmployeeDocumentUploadRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDocumentUploadRequest {
  @NotNull private Long employeeId;
  @NotBlank private String documentTypeCode;
  @NotBlank private String cloudinaryPublicId;
  @NotBlank private String secureUrl;
  @Positive private Long sizeBytes;
  @Size(max = 80) private String contentType;
}
