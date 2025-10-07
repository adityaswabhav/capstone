package com.aurionpro.service.impl;

import com.aurionpro.dto.request.BankAccUpdateCreateRequest;
import com.aurionpro.dto.request.EmployeeDocumentReviewRequest;
import com.aurionpro.entity.employee.BankAccountUpdateRequest;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.employee.EmployeeDocument;
import com.aurionpro.entity.enums.DocumentStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;
import com.aurionpro.mapper.BankAccountUpdateMapper;
import com.aurionpro.repository.BankAccUpdateReqRepository;
import com.aurionpro.repository.EmployeeDocumentRepository;
import com.aurionpro.repository.EmployeeRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.ref.RefDocTypeRepository;
import com.aurionpro.service.BankAccountUpdateService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccUpdateServiceImpl implements BankAccountUpdateService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;
    private final RefDocTypeRepository refDocumentTypeRepository;
    private final EmployeeDocumentRepository employeeDocumentRepository;
    private final BankAccUpdateReqRepository bankAccountUpdateRequestRepository;

    @Override
    @Transactional
    public BankAccountUpdateRequest createEntity(Long orgId, BankAccUpdateCreateRequest request) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Employee emp = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (!emp.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Employee not in organization");

        RefDocumentType type = refDocumentTypeRepository.findByCodeAndActiveTrue(request.getProofDocumentTypeCode())
                .orElseThrow(() -> new EntityNotFoundException("Invalid proof document type"));

        EmployeeDocument proof = new EmployeeDocument();
        proof.setOrganization(org);
        proof.setEmployee(emp);
        proof.setDocumentType(type);
        proof.setCloudinaryPublicId(request.getProofCloudinaryPublicId());
        proof.setSecureUrl(request.getProofSecureUrl());
        proof.setStatus(DocumentStatus.PENDING);
        proof.setSizeBytes(request.getProofSizeBytes());
        proof.setContentType(request.getProofContentType());
        employeeDocumentRepository.save(proof);

        BankAccountUpdateRequest upd = BankAccountUpdateMapper.toEntity(request, org, emp, proof);
        return bankAccountUpdateRequestRepository.save(upd);
    }

    @Override
    @Transactional
    public BankAccountUpdateRequest review(Long orgId, Long employeeId, Long id, EmployeeDocumentReviewRequest request) {
        BankAccountUpdateRequest upd = bankAccountUpdateRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));
        if (!upd.getOrganization().getId().equals(orgId) || !upd.getEmployee().getId().equals(employeeId))
            throw new IllegalArgumentException("Request not in scope");

        if (request.isApprove()) {
            upd.setStatus(DocumentStatus.APPROVED);
            var masked = upd.getNewAccountMasked();
            if (masked != null && masked.length() >= 4) {
                String last4 = masked.substring(masked.length() - 4);
                upd.getEmployee().setBankAccountLast4(last4);
            }
        } else {
            upd.setStatus(DocumentStatus.REJECTED);
            upd.setReviewerNotes(request.getNote());
        }
        return bankAccountUpdateRequestRepository.save(upd);
    }
}
