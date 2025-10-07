package com.aurionpro.service.impl;

import com.aurionpro.dto.request.DepartmentCreateRequest;
import com.aurionpro.dto.request.DepartmentUpdateRequest;
import com.aurionpro.entity.org.Department;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.mapper.DepartmentMapper;
import com.aurionpro.repository.DepartmentRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public Department createEntity(DepartmentCreateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Department d = DepartmentMapper.toEntity(request, org);
        return departmentRepository.save(d);
    }

    @Override
    @Transactional
    public Department updateEntity(DepartmentUpdateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Department d = departmentRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        if (!d.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Department does not belong to this organization");
        return departmentRepository.save(DepartmentMapper.toEntity(request, org, d));
    }

    @Override
    public List<Department> findAllByOrg(Long organizationId) {
        return departmentRepository.findByOrganizationId(organizationId);
    }

    @Override
    @Transactional
    public void delete(Long organizationId, Long id) {
        Department d = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        if (!d.getOrganization().getId().equals(organizationId))
            throw new IllegalArgumentException("Department does not belong to this organization");
        departmentRepository.delete(d);
    }
}
