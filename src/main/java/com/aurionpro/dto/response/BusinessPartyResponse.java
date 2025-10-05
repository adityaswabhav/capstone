// File: src/main/java/com/aurionpro/dto/response/BusinessPartyResponse.java
package com.aurionpro.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BusinessPartyResponse {
  private Long id;
  private Long organizationId;
  private String typeCode;
  private String typeName;
  private String name;
  private String contactEmail;
  private String contactPhone;
  private String ifsc;
  private String accountMasked;
}
