package com.aurionpro.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aurionpro.dto.request.ConcernCreateRequest;
import com.aurionpro.dto.response.ConcernResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.enums.ConcernStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;
import com.aurionpro.entity.support.Concern;
import com.aurionpro.entity.support.ConcernAttachment;

public class ConcernMapper {

    private ConcernMapper() {}

    // === Entity -> Response ===

    public static ConcernResponse toResponse(Concern c) {
        if (c == null) return null;
        List<ConcernResponse.ConcernAttachmentItem> items = null;
        if (c.getAttachments() != null) {
            items = c.getAttachments().stream()
                    .map(ConcernMapper::toItem)
                    .collect(Collectors.toList());
        }
        return ConcernResponse.builder()
                .id(c.getId())
                .organizationId(c.getOrganization() != null ? c.getOrganization().getId() : null)
                .employeeId(c.getEmployee() != null ? c.getEmployee().getId() : null)
                .subject(c.getSubject())
                .description(c.getDescription())
                .status(c.getStatus() != null ? c.getStatus().name() : null)
                .resolutionNote(c.getResolutionNote())
                .attachments(items)
                .build();
    }

    private static ConcernResponse.ConcernAttachmentItem toItem(ConcernAttachment a) {
        if (a == null) return null;
        return ConcernResponse.ConcernAttachmentItem.builder()
                .id(a.getId())
                .documentTypeCode(a.getDocumentType() != null ? a.getDocumentType().getCode() : null)
                .documentTypeName(a.getDocumentType() != null ? a.getDocumentType().getDisplayName() : null)
                .secureUrl(a.getSecureUrl())
                .build();
    }

    // === Request -> Entity ===

    public static Concern toEntity(ConcernCreateRequest r, Organization org, Employee emp) {
        if (r == null || org == null || emp == null) return null;
        Concern c = new Concern();
        c.setOrganization(org);
        c.setEmployee(emp);
        c.setSubject(r.getSubject());
        c.setDescription(r.getDescription());
        c.setStatus(ConcernStatus.OPEN);
        return c;
    }

    public static ConcernAttachment toEntity(Concern concern, RefDocumentType type, ConcernCreateRequest.Attachment a) {
        if (concern == null || type == null || a == null) return null;
        ConcernAttachment e = new ConcernAttachment();
        e.setOrganization(concern.getOrganization());
        e.setConcern(concern);
        e.setDocumentType(type);
        e.setCloudinaryPublicId(a.getCloudinaryPublicId());
        e.setSecureUrl(a.getSecureUrl());
        e.setSizeBytes(a.getSizeBytes());
        e.setContentType(a.getContentType());
        return e;
    }

    public static List<ConcernAttachment> toEntities(Concern concern,
                                                     List<RefDocumentType> typesInSameOrder,
                                                     List<ConcernCreateRequest.Attachment> atts) {
        if (concern == null || typesInSameOrder == null || atts == null) return List.of();
        List<ConcernAttachment> out = new ArrayList<>();
        for (int i = 0; i < atts.size(); i++) {
            out.add(toEntity(concern, typesInSameOrder.get(i), atts.get(i)));
        }
        return out;
    }
}
