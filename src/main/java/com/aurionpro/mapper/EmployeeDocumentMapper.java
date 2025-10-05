package com.aurionpro.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.aurionpro.dto.request.EmployeeDocumentUploadRequest;
import com.aurionpro.dto.response.EmployeeDocResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.employee.EmployeeDocument;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;

public class EmployeeDocumentMapper {

    private EmployeeDocumentMapper() {}

    // === Entity -> Response ===

    public static EmployeeDocResponse toResponse(EmployeeDocument d) {
        if (d == null) return null;
        return EmployeeDocResponse.builder()
                .id(d.getId())
                .employeeId(d.getEmployee() != null ? d.getEmployee().getId() : null)
                .organizationId(d.getOrganization() != null ? d.getOrganization().getId() : null)
                .documentTypeCode(d.getDocumentType() != null ? d.getDocumentType().getCode() : null)
                .documentTypeName(d.getDocumentType() != null ? d.getDocumentType().getDisplayName() : null)
                .status(d.getStatus() != null ? d.getStatus().name() : null)
                .secureUrl(d.getSecureUrl())
                .build();
    }

    public static List<EmployeeDocResponse> toResponseList(List<EmployeeDocument> list) {
        if (list == null) return null;
        return list.stream().map(EmployeeDocumentMapper::toResponse).collect(Collectors.toList());
    }

    // === Request -> Entity ===

    public static EmployeeDocument toEntity(EmployeeDocumentUploadRequest r,
                                            Organization org,
                                            Employee employee,
                                            RefDocumentType type) {
        if (r == null || org == null || employee == null || type == null) return null;
        EmployeeDocument e = new EmployeeDocument();
        e.setOrganization(org);
        e.setEmployee(employee);
        e.setDocumentType(type);
        e.setCloudinaryPublicId(r.getCloudinaryPublicId());
        e.setSecureUrl(r.getSecureUrl());
        e.setStatus(DocumentStatus.PENDING);
        e.setSizeBytes(r.getSizeBytes());
        e.setContentType(r.getContentType());
        return e;
    }
}
