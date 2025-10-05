// File: src/main/java/com/aurionpro/dto/response/OrgDocumentResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrgDocumentResponse {
  private Long id;
  private Long organizationId;
  private String documentTypeCode;
  private String documentTypeName;
  private String status;
  private String secureUrl;
}
