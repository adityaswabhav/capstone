// File: src/main/java/com/aurionpro/dto/request/BusinessPartyCreateRequest.java
package com.aurionpro.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BusinessPartyCreateRequest {
  @NotNull private Long organizationId;
  @NotBlank private String typeCode;  // from RefBusinessPartyType
  @NotBlank private String name;
  @Email private String contactEmail;
  @Size(max = 20) private String contactPhone;
  @Size(max = 32) private String ifsc;
  @Size(max = 34) private String accountMasked;
}
