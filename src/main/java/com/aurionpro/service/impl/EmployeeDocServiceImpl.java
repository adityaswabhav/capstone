package com.aurionpro.service.impl;

import com.aurionpro.dto.request.EmployeeDocumentReviewRequest;
import com.aurionpro.dto.request.EmployeeDocumentUploadRequest;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.employee.EmployeeDocument;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;
import com.aurionpro.mapper.EmployeeDocumentMapper;
import com.aurionpro.repository.EmployeeDocumentRepository;
import com.aurionpro.repository.EmployeeRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.ref.RefDocTypeRepository;
import com.aurionpro.service.EmployeeDocumentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDocServiceImpl implements EmployeeDocumentService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeDocumentRepository employeeDocumentRepository;
    private final RefDocTypeRepository refDocumentTypeRepository;

    @Override
    @Transactional
    public EmployeeDocument uploadEntity(Long orgId, EmployeeDocumentUploadRequest request) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Employee emp = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (!emp.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Employee not in organization");

        RefDocumentType type = refDocumentTypeRepository.findByCodeAndActiveTrue(request.getDocumentTypeCode())
                .orElseThrow(() -> new EntityNotFoundException("Invalid document type"));

        EmployeeDocument doc = EmployeeDocumentMapper.toEntity(request, org, emp, type);
        return employeeDocumentRepository.save(doc);
    }

    @Override
    @Transactional
    public EmployeeDocument review(Long orgId, Long employeeId, Long docId, EmployeeDocumentReviewRequest request) {
        EmployeeDocument doc = employeeDocumentRepository.findById(docId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        if (!doc.getOrganization().getId().equals(orgId) || !doc.getEmployee().getId().equals(employeeId))
            throw new IllegalArgumentException("Document not in scope");

        if (request.isApprove()) {
            doc.setStatus(DocumentStatus.APPROVED);
            doc.setNotes(null);
        } else {
            doc.setStatus(DocumentStatus.REJECTED);
            doc.setNotes(request.getNote());
        }
        return employeeDocumentRepository.save(doc);
    }

    @Override
    public List<EmployeeDocument> findAll(Long orgId, Long employeeId) {
        return employeeDocumentRepository.findByOrganizationIdAndEmployeeId(orgId, employeeId);
    }
}
