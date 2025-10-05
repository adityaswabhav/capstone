package com.aurionpro.repository;

import com.aurionpro.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByOrganizationId(Long organizationId);
    List<Employee> findByOrganizationIdAndDepartmentId(Long organizationId, Long departmentId);
}
