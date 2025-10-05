package com.aurionpro.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.aurionpro.dto.request.OrgRegistrationRequest;
import com.aurionpro.dto.response.OrgDocumentResponse;
import com.aurionpro.dto.response.OrganizationResponse;
import com.aurionpro.entity.bank.Bank;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.enums.OrganizationStatus;
import com.aurionpro.entity.org.OrgDocument;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;

public class OrganizationMapper {

    private OrganizationMapper() {}

    // === Entity -> Response ===

    public static OrganizationResponse toResponse(Organization o) {
        if (o == null) return null;
        return OrganizationResponse.builder()
                .id(o.getId())
                .name(o.getName())
                .status(o.getStatus() != null ? o.getStatus().name() : null)
                .rejectionReason(o.getRejectionReason())
                .contactEmail(o.getContactEmail())
                .contactPhone(o.getContactPhone())
                .build();
    }

    public static List<OrganizationResponse> toResponseList(List<Organization> list) {
        if (list == null) return null;
        return list.stream().map(OrganizationMapper::toResponse).collect(Collectors.toList());
    }

    public static OrgDocumentResponse toResponse(OrgDocument d) {
        if (d == null) return null;
        return OrgDocumentResponse.builder()
                .id(d.getId())
                .organizationId(d.getOrganization() != null ? d.getOrganization().getId() : null)
                .documentTypeCode(d.getDocumentType() != null ? d.getDocumentType().getCode() : null)
                .documentTypeName(d.getDocumentType() != null ? d.getDocumentType().getDisplayName() : null)
                .status(d.getStatus() != null ? d.getStatus().name() : null)
                .secureUrl(d.getSecureUrl())
                .build();
    }

    public static List<OrgDocumentResponse> toOrgDocResponseList(List<OrgDocument> list) {
        if (list == null) return null;
        return list.stream().map(OrganizationMapper::toResponse).collect(Collectors.toList());
    }

    // === Request -> Entity ===

    public static Organization toEntity(OrgRegistrationRequest r, Bank bank) {
        if (r == null) return null;
        Organization o = new Organization();
        o.setName(r.getName());
        o.setContactEmail(r.getContactEmail());
        o.setContactPhone(r.getContactPhone());
        o.setStatus(OrganizationStatus.PENDING);
        o.setBank(bank);
        return o;
    }

    public static OrgDocument toEntity(Organization org,
                                       RefDocumentType type,
                                       OrgRegistrationRequest.OrgRegistrationDocument d) {
        if (org == null || type == null || d == null) return null;
        OrgDocument e = new OrgDocument();
        e.setOrganization(org);
        e.setDocumentType(type);
        e.setCloudinaryPublicId(d.getCloudinaryPublicId());
        e.setSecureUrl(d.getSecureUrl());
        e.setStatus(DocumentStatus.PENDING);
        e.setSizeBytes(d.getSizeBytes());
        e.setContentType(d.getContentType());
        return e;
    }
}
