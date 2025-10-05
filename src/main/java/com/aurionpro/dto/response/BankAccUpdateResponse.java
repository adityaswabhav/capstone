// File: src/main/java/com/aurionpro/dto/response/BankAccountUpdateResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccUpdateResponse {
  private Long id;
  private Long employeeId;
  private Long organizationId;
  private String newAccountMasked;
  private String status;
  private Long proofDocumentId;
}
