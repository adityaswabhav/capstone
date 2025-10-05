// File: src/main/java/com/aurionpro/dto/request/ConcernCreateRequest.java
package com.aurionpro.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConcernCreateRequest {
  @NotNull private Long organizationId;
  @NotNull private Long employeeId;
  @NotBlank private String subject;
  @NotBlank private String description;
  private List<Attachment> attachments;

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class Attachment {
    @NotBlank private String documentTypeCode; // CONCERN_ATTACHMENT
    @NotBlank private String cloudinaryPublicId;
    @NotBlank private String secureUrl;
    @Positive private Long sizeBytes;
    @Size(max = 80) private String contentType;
  }
}
