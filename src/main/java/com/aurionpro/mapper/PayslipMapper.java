package com.aurionpro.mapper;

import com.aurionpro.dto.response.PayslipResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.entity.payroll.Payslip;

public class PayslipMapper {

    private PayslipMapper() {}

    // === Entity -> Response ===

    public static PayslipResponse toResponse(Payslip p) {
        if (p == null) return null;
        return PayslipResponse.builder()
                .id(p.getId())
                .organizationId(p.getOrganization() != null ? p.getOrganization().getId() : null)
                .payrollCycleId(p.getPayrollCycle() != null ? p.getPayrollCycle().getId() : null)
                .employeeId(p.getEmployee() != null ? p.getEmployee().getId() : null)
                .secureUrl(p.getSecureUrl())
                .build();
    }

    // === Helper to build entity ===

    public static Payslip build(Organization org, PayrollCycle cycle, Employee emp,
                                String publicId, String secureUrl) {
        Payslip p = new Payslip();
        p.setOrganization(org);
        p.setPayrollCycle(cycle);
        p.setEmployee(emp);
        p.setCloudinaryPublicId(publicId);
        p.setSecureUrl(secureUrl);
        return p;
    }
}
