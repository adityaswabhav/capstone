// File: src/main/java/com/aurionpro/dto/request/ConcernResolveRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConcernResolveRequest {
  @NotNull private Long concernId;
  private String resolutionNote;
  private String reasonCode;
}
