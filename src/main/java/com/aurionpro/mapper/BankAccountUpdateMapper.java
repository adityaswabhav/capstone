package com.aurionpro.mapper;

import com.aurionpro.dto.request.BankAccUpdateCreateRequest;
import com.aurionpro.dto.response.BankAccUpdateResponse;
import com.aurionpro.entity.employee.BankAccountUpdateRequest;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.employee.EmployeeDocument;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.org.Organization;

public class BankAccountUpdateMapper {

    private BankAccountUpdateMapper() {}

    // === Entity -> Response ===

    public static BankAccUpdateResponse toResponse(BankAccountUpdateRequest e) {
        if (e == null) return null;
        return BankAccUpdateResponse.builder()
                .id(e.getId())
                .employeeId(e.getEmployee() != null ? e.getEmployee().getId() : null)
                .organizationId(e.getOrganization() != null ? e.getOrganization().getId() : null)
                .newAccountMasked(e.getNewAccountMasked())
                .status(e.getStatus() != null ? e.getStatus().name() : null)
                .proofDocumentId(e.getProofDocument() != null ? e.getProofDocument().getId() : null)
                .build();
    }

    // === Request -> Entity ===

    public static BankAccountUpdateRequest toEntity(BankAccUpdateCreateRequest r,
                                                    Organization org,
                                                    Employee emp,
                                                    EmployeeDocument proofDoc) {
        if (r == null || org == null || emp == null) return null;
        BankAccountUpdateRequest e = new BankAccountUpdateRequest();
        e.setOrganization(org);
        e.setEmployee(emp);
        e.setNewAccountMasked(r.getNewAccountMasked());
        e.setProofDocument(proofDoc);
        e.setStatus(DocumentStatus.PENDING);
        return e;
    }
}
