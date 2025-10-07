package com.aurionpro.service;

import com.aurionpro.entity.payroll.Payslip;

import java.util.List;

public interface PayslipService {
    Payslip generateForEmployee(Long orgId, Long payrollCycleId, Long employeeId);
    List<Payslip> generateForCycle(Long orgId, Long payrollCycleId);
}
