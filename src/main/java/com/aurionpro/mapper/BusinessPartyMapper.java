package com.aurionpro.mapper;

import com.aurionpro.dto.request.BusinessPartyCreateRequest;
import com.aurionpro.dto.response.BusinessPartyResponse;
import com.aurionpro.entity.finance.BusinessParty;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefBusinessPartyType;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessPartyMapper {

    private BusinessPartyMapper() {}

    // === Entity -> Response ===

    public static BusinessPartyResponse toResponse(BusinessParty b) {
        if (b == null) return null;
        return BusinessPartyResponse.builder()
                .id(b.getId())
                .organizationId(b.getOrganization() != null ? b.getOrganization().getId() : null)
                .typeCode(b.getType() != null ? b.getType().getCode() : null)
                .typeName(b.getType() != null ? b.getType().getDisplayName() : null)
                .name(b.getName())
                .contactEmail(b.getContactEmail())
                .contactPhone(b.getContactPhone())
                .ifsc(b.getIfsc())
                .accountMasked(b.getAccountMasked())
                .build();
    }

    public static List<BusinessPartyResponse> toResponseList(List<BusinessParty> list) {
        if (list == null) return null;
        return list.stream().map(BusinessPartyMapper::toResponse).collect(Collectors.toList());
    }

    // === Request -> Entity ===

    public static BusinessParty toEntity(BusinessPartyCreateRequest r, Organization org, RefBusinessPartyType type) {
        if (r == null || org == null || type == null) return null;
        BusinessParty b = new BusinessParty();
        b.setOrganization(org);
        b.setType(type);
        b.setName(r.getName());
        b.setContactEmail(r.getContactEmail());
        b.setContactPhone(r.getContactPhone());
        b.setIfsc(r.getIfsc());
        b.setAccountMasked(r.getAccountMasked());
        return b;
    }
}
