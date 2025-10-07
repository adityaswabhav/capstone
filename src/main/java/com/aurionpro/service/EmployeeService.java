package com.aurionpro.service;

import com.aurionpro.dto.request.EmployeeCreateRequest;
import com.aurionpro.dto.request.EmployeeUpdateRequest;
import com.aurionpro.entity.employee.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEntity(EmployeeCreateRequest request);
    Employee updateEntity(EmployeeUpdateRequest request);
    List<Employee> findAllByOrg(Long organizationId, Long departmentId);
    Employee get(Long organizationId, Long id);
}
