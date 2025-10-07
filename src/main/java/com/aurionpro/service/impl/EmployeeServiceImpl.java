package com.aurionpro.service.impl;

import com.aurionpro.dto.request.EmployeeCreateRequest;
import com.aurionpro.dto.request.EmployeeUpdateRequest;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.enums.RoleName;
import com.aurionpro.entity.org.Department;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.security.Role;
import com.aurionpro.entity.security.User;
import com.aurionpro.mapper.EmployeeMapper;
import com.aurionpro.repository.DepartmentRepository;
import com.aurionpro.repository.EmployeeRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.security.RoleRepository;
import com.aurionpro.repository.security.UserRepository;
import com.aurionpro.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final OrganizationRepository organizationRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Employee createEntity(EmployeeCreateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        Department dept = null;
        if (request.getDepartmentId() != null) {
            dept = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            if (!dept.getOrganization().getId().equals(org.getId()))
                throw new IllegalArgumentException("Department not in organization");
        }

        Role empRole = roleRepository.findByName(RoleName.EMPLOYEE)
                .orElseThrow(() -> new IllegalStateException("EMPLOYEE role missing"));

        String rawPassword = UUID.randomUUID().toString().substring(0, 8);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setRole(empRole);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        userRepository.save(user);

        Employee employee = EmployeeMapper.toEntity(request, org, dept, user);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEntity(EmployeeUpdateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Employee e = employeeRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (!e.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Employee not in organization");

        Department dept = null;
        if (request.getDepartmentId() != null) {
            dept = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            if (!dept.getOrganization().getId().equals(org.getId()))
                throw new IllegalArgumentException("Department not in organization");
        }
        return employeeRepository.save(EmployeeMapper.toEntity(request, org, dept, e));
    }

    @Override
    public List<Employee> findAllByOrg(Long organizationId, Long departmentId) {
        if (departmentId != null) {
            return employeeRepository.findByOrganizationIdAndDepartmentId(organizationId, departmentId);
        }
        return employeeRepository.findByOrganizationId(organizationId);
    }

    @Override
    public Employee get(Long organizationId, Long id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (!e.getOrganization().getId().equals(organizationId))
            throw new IllegalArgumentException("Employee not in organization");
        return e;
    }
}
