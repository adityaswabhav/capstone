// File: src/main/java/com/aurionpro/dto/response/ConcernResponse.java
package com.aurionpro.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConcernResponse {
  private Long id;
  private Long organizationId;
  private Long employeeId;
  private String subject;
  private String description;
  private String status;
  private String resolutionNote;
  private List<ConcernAttachmentItem> attachments;

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class ConcernAttachmentItem {
    private Long id;
    private String documentTypeCode;
    private String documentTypeName;
    private String secureUrl;
  }
}
