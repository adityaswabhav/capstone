package com.aurionpro.service.impl;

import com.aurionpro.dto.request.ConcernCreateRequest;
import com.aurionpro.dto.request.ConcernResolveRequest;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.enums.ConcernStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefDocumentType;
import com.aurionpro.entity.support.Concern;
import com.aurionpro.entity.support.ConcernAttachment;
import com.aurionpro.mapper.ConcernMapper;
import com.aurionpro.repository.EmployeeRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.ref.RefDocTypeRepository;
import com.aurionpro.repository.support.ConcernAttachmentRepository;
import com.aurionpro.repository.support.ConcernRepository;
import com.aurionpro.service.ConcernService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcernServiceImpl implements ConcernService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;
    private final RefDocTypeRepository refDocumentTypeRepository;
    private final ConcernRepository concernRepository;
    private final ConcernAttachmentRepository concernAttachmentRepository;

    @Override
    @Transactional
    public Concern createEntity(ConcernCreateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Employee emp = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (!emp.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Employee not in organization");

        Concern c = ConcernMapper.toEntity(request, org, emp);
        concernRepository.save(c);

        if (request.getAttachments() != null && !request.getAttachments().isEmpty()) {
            List<ConcernAttachment> toSave = new ArrayList<>();
            for (var a : request.getAttachments()) {
                RefDocumentType type = refDocumentTypeRepository.findByCodeAndActiveTrue(a.getDocumentTypeCode())
                        .orElseThrow(() -> new EntityNotFoundException("Invalid attachment type: " + a.getDocumentTypeCode()));
                toSave.add(ConcernMapper.toEntity(c, type, a));
            }
            concernAttachmentRepository.saveAll(toSave);
            try { c.setAttachments(toSave); } catch (Exception ignored) {}
        }
        return c;
    }

    @Override
    public List<Concern> findAll(Long orgId, Long employeeId, String status) {
        if (employeeId != null && status != null) {
            return concernRepository.findByOrganizationIdAndEmployeeIdAndStatus(orgId, employeeId, ConcernStatus.valueOf(status));
        } else if (employeeId != null) {
            return concernRepository.findByOrganizationIdAndEmployeeId(orgId, employeeId);
        } else if (status != null) {
            return concernRepository.findByOrganizationIdAndStatus(orgId, ConcernStatus.valueOf(status));
        }
        return concernRepository.findByOrganizationId(orgId);
    }

    @Override
    @Transactional
    public Concern resolve(Long orgId, Long id, ConcernResolveRequest request) {
        Concern c = concernRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Concern not found"));
        if (!c.getOrganization().getId().equals(orgId))
            throw new IllegalArgumentException("Concern not in organization");
        c.setStatus(ConcernStatus.RESOLVED);
        c.setResolutionNote(request.getResolutionNote());
        return concernRepository.save(c);
    }
}
