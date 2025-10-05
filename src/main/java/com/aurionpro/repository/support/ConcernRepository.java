package com.aurionpro.repository.support;

import com.aurionpro.entity.enums.ConcernStatus;
import com.aurionpro.entity.support.Concern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcernRepository extends JpaRepository<Concern, Long> {
    List<Concern> findByOrganizationId(Long organizationId);
    List<Concern> findByOrganizationIdAndEmployeeId(Long organizationId, Long employeeId);
    List<Concern> findByOrganizationIdAndStatus(Long organizationId, ConcernStatus status);
    List<Concern> findByOrganizationIdAndEmployeeIdAndStatus(Long organizationId, Long employeeId, ConcernStatus status);
}
