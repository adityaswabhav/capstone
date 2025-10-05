// File: src/main/java/com/aurionpro/dto/common/PageRequestDTO.java
package com.aurionpro.dto.common;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PageRequestDTO {
  @NotNull @Min(0)
  private Integer page;

  @NotNull @Min(1) @Max(200)
  private Integer size;

  private String sort;   // e.g., "name,asc"
  private String search;
}
