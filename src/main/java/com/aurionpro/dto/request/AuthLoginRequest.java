// File: src/main/java/com/aurionpro/dto/request/AuthLoginRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthLoginRequest {
  @Email @NotBlank private String email;
  @NotBlank private String password;
  @NotBlank private String captchaToken;
}
