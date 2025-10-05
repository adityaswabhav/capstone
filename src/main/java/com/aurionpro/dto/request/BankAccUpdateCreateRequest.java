// File: src/main/java/com/aurionpro/dto/request/BankAccountUpdateCreateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccUpdateCreateRequest {
  @NotNull private Long employeeId;
  @NotBlank private String newAccountMasked;

  @NotBlank private String proofDocumentTypeCode; // EMP_BANK_PROOF
  @NotBlank private String proofCloudinaryPublicId;
  @NotBlank private String proofSecureUrl;
  @Positive private Long proofSizeBytes;
  @Size(max = 80) private String proofContentType;
}
