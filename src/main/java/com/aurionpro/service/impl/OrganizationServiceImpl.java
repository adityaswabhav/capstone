package com.aurionpro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aurionpro.dto.request.OrgApprovalRequest;
import com.aurionpro.dto.request.OrgRegistrationRequest;
import com.aurionpro.entity.bank.Bank;
import com.aurionpro.entity.enums.OrganizationStatus;
import com.aurionpro.entity.org.OrgDocument;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;
import com.aurionpro.mapper.OrganizationMapper;
import com.aurionpro.repository.OrgDocumentRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.bank.BankRepository;
import com.aurionpro.repository.ref.RefDocTypeRepository;
import com.aurionpro.service.OrganizationService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrgDocumentRepository orgDocumentRepository;
    private final RefDocTypeRepository refDocumentTypeRepository;
    private final BankRepository bankRepository;

    @Override
    @Transactional
    public Organization registerEntity(OrgRegistrationRequest request) {
        if (organizationRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Organization name already exists: " + request.getName());
        }
        Bank bank = bankRepository.findDefaultBank()
                .orElseThrow(() -> new IllegalStateException("No bank initialized. Seed a Bank first."));
        Organization org = OrganizationMapper.toEntity(request, bank);
        organizationRepository.save(org);

        for (var d : request.getDocuments()) {
            RefDocumentType type = refDocumentTypeRepository
                    .findByCodeAndActiveTrue(d.getDocumentTypeCode())
                    .orElseThrow(() -> new EntityNotFoundException("Unknown document type: " + d.getDocumentTypeCode()));
            OrgDocument doc = OrganizationMapper.toEntity(org, type, d);
            orgDocumentRepository.save(doc);
        }
        return org;
    }

    @Override
    public List<Organization> findByStatus(OrganizationStatus status) {
        return organizationRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public Organization decide(Long orgId, OrgApprovalRequest request) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found: " + orgId));
        if (request.isApprove()) {
            org.setStatus(OrganizationStatus.APPROVED);
            org.setRejectionReason(null);
        } else {
            org.setStatus(OrganizationStatus.REJECTED);
            org.setRejectionReason(request.getNote());
        }
        return organizationRepository.save(org);
    }
}
