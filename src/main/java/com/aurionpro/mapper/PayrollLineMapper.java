package com.aurionpro.mapper;

import com.aurionpro.dto.response.PayrollLineResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.entity.payroll.PayrollLine;

import java.util.List;
import java.util.stream.Collectors;

public class PayrollLineMapper {

    private PayrollLineMapper() {}

    // === Entity -> Response ===

    public static PayrollLineResponse toResponse(PayrollLine p) {
        if (p == null) return null;
        return PayrollLineResponse.builder()
                .id(p.getId())
                .organizationId(p.getOrganization() != null ? p.getOrganization().getId() : null)
                .payrollCycleId(p.getPayrollCycle() != null ? p.getPayrollCycle().getId() : null)
                .employeeId(p.getEmployee() != null ? p.getEmployee().getId() : null)
                .employeeCode(p.getEmployee() != null ? p.getEmployee().getCode() : null)
                .employeeName(p.getEmployee() != null && p.getEmployee().getUser() != null ? p.getEmployee().getUser().getFullName() : null)
                .earningsTotal(p.getEarningsTotal())
                .deductionsTotal(p.getDeductionsTotal())
                .net(p.getNet())
                .remarks(p.getRemarks())
                .build();
    }

    public static List<PayrollLineResponse> toResponseList(List<PayrollLine> list) {
        if (list == null) return null;
        return list.stream().map(PayrollLineMapper::toResponse).collect(Collectors.toList());
    }

    // === Helpers to build a line (service will compute amounts) ===

    public static PayrollLine build(Organization org, PayrollCycle cycle, Employee emp,
                                    long earningsTotal, long deductionsTotal, long net, String remarks) {
        PayrollLine p = new PayrollLine();
        p.setOrganization(org);
        p.setPayrollCycle(cycle);
        p.setEmployee(emp);
        p.setEarningsTotal(earningsTotal);
        p.setDeductionsTotal(deductionsTotal);
        p.setNet(net);
        p.setRemarks(remarks);
        return p;
    }
}
