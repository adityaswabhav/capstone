package com.aurionpro.service;

import com.aurionpro.dto.request.BankAccUpdateCreateRequest;
import com.aurionpro.dto.request.EmployeeDocumentReviewRequest;
import com.aurionpro.entity.employee.BankAccountUpdateRequest;

public interface BankAccountUpdateService {
    BankAccountUpdateRequest createEntity(Long orgId, BankAccUpdateCreateRequest request);
    BankAccountUpdateRequest review(Long orgId, Long employeeId, Long id, EmployeeDocumentReviewRequest request);
}
