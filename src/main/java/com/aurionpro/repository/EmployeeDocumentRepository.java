package com.aurionpro.repository;

import com.aurionpro.entity.employee.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
    List<EmployeeDocument> findByOrganizationIdAndEmployeeId(Long organizationId, Long employeeId);
}
