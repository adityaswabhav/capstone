// File: src/main/java/com/aurionpro/dto/common/ApiErrorResponse.java
package com.aurionpro.dto.common;

import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiErrorResponse {
  private Instant timestamp;
  private int status;
  private String error;
  private String message;
  private String path;
}
