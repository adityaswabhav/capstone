package com.aurionpro.repository.payroll;

import com.aurionpro.entity.payroll.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayslipRepository extends JpaRepository<Payslip, Long> {
    List<Payslip> findByOrganizationIdAndPayrollCycleId(Long organizationId, Long payrollCycleId);
}
