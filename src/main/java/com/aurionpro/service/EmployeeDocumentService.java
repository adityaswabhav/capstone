package com.aurionpro.service;

import com.aurionpro.dto.request.EmployeeDocumentReviewRequest;
import com.aurionpro.dto.request.EmployeeDocumentUploadRequest;
import com.aurionpro.entity.employee.EmployeeDocument;

import java.util.List;

public interface EmployeeDocumentService {
    EmployeeDocument uploadEntity(Long orgId, EmployeeDocumentUploadRequest request);
    EmployeeDocument review(Long orgId, Long employeeId, Long docId, EmployeeDocumentReviewRequest request);
    List<EmployeeDocument> findAll(Long orgId, Long employeeId);
}
