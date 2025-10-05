// File: src/main/java/com/aurionpro/dto/response/TokenResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TokenResponse {
  private String accessToken;
  private long expiresInMs;
  private String tokenType; // "Bearer"
}
