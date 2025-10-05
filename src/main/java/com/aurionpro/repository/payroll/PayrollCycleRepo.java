package com.aurionpro.repository.payroll;

import com.aurionpro.entity.payroll.PayrollCycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollCycleRepo extends JpaRepository<PayrollCycle, Long> {
    boolean existsByOrganizationIdAndPeriodMonthAndPeriodYear(Long organizationId, int periodMonth, int periodYear);
    List<PayrollCycle> findByOrganizationId(Long organizationId);
    List<PayrollCycle> findByOrganizationIdAndPeriodMonthAndPeriodYear(Long organizationId, int periodMonth, int periodYear);
}
