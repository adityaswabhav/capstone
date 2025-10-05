// File: src/main/java/com/aurionpro/dto/request/OrgRegistrationRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrgRegistrationRequest {
  @NotBlank private String name;
  @Email private String contactEmail;
  @Size(max = 20) private String contactPhone;
  @NotNull @Size(min = 1) private List<OrgRegistrationDocument> documents;

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class OrgRegistrationDocument {
    @NotBlank private String documentTypeCode; // e.g., ORG_REG_DOC
    @NotBlank private String cloudinaryPublicId;
    @NotBlank private String secureUrl;
    @Positive private Long sizeBytes;
    @Size(max = 80) private String contentType;
  }
}
