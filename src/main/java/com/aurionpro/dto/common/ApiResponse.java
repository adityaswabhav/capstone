// File: src/main/java/com/aurionpro/dto/common/ApiResponse.java
package com.aurionpro.dto.common;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiResponse<T> {
  private boolean successfull;
  private String message;
  private T data;
}
