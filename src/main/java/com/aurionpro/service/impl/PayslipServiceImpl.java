package com.aurionpro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.entity.payroll.Payslip;
import com.aurionpro.repository.EmployeeRepository;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.payroll.PayrollCycleRepo;
import com.aurionpro.repository.payroll.PayslipRepository; // Create this repository (see note below)
import com.aurionpro.service.PayslipService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayslipServiceImpl implements PayslipService {

    private final OrganizationRepository organizationRepository;
    private final PayrollCycleRepo payrollCycleRepository;
    private final EmployeeRepository employeeRepository;
    private final PayslipRepository payslipRepository;

    @Override
    @Transactional
    public Payslip generateForEmployee(Long orgId, Long payrollCycleId, Long employeeId) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        PayrollCycle cycle = payrollCycleRepository.findById(payrollCycleId)
                .orElseThrow(() -> new EntityNotFoundException("Payroll cycle not found"));
        if (!cycle.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Cycle not in organization");
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (!emp.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Employee not in organization");

        // TODO: Generate PDF bytes & upload to Cloudinary; capture publicId + secureUrl
        Payslip slip = new Payslip();
        slip.setOrganization(org);
        slip.setPayrollCycle(cycle);
        slip.setEmployee(emp);
        slip.setCloudinaryPublicId("pending");
        slip.setSecureUrl("#"); // placeholder

        return payslipRepository.save(slip);
    }

    @Override
    @Transactional
    public List<Payslip> generateForCycle(Long orgId, Long payrollCycleId) {
        // TODO: iterate employees from cycle lines and call generateForEmployee
        // For now, return existing payslips for the cycle
        return payslipRepository.findByOrganizationIdAndPayrollCycleId(orgId, payrollCycleId);
    }
}
