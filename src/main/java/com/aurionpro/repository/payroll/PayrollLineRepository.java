package com.aurionpro.repository.payroll;

import com.aurionpro.entity.payroll.PayrollLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollLineRepository extends JpaRepository<PayrollLine, Long> {
    List<PayrollLine> findByOrganizationIdAndPayrollCycleId(Long organizationId, Long payrollCycleId);
}
