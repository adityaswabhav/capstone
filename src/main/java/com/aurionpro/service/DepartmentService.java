package com.aurionpro.service;

import com.aurionpro.dto.request.DepartmentCreateRequest;
import com.aurionpro.dto.request.DepartmentUpdateRequest;
import com.aurionpro.entity.org.Department;

import java.util.List;

public interface DepartmentService {
    Department createEntity(DepartmentCreateRequest request);
    Department updateEntity(DepartmentUpdateRequest request);
    List<Department> findAllByOrg(Long organizationId);
    void delete(Long organizationId, Long id);
}
