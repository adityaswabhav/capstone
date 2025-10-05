package com.aurionpro.mapper;

import com.aurionpro.dto.request.SalaryStructureUpsertRequest;
import com.aurionpro.dto.response.SalaryStructureResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.SalaryStructure;

public class SalaryStructureMapper {

    private SalaryStructureMapper() {}

    // === Entity -> Response ===

    public static SalaryStructureResponse toResponse(SalaryStructure s) {
        if (s == null) return null;
        return SalaryStructureResponse.builder()
                .id(s.getId())
                .organizationId(s.getOrganization() != null ? s.getOrganization().getId() : null)
                .employeeId(s.getEmployee() != null ? s.getEmployee().getId() : null)
                .basic(s.getBasic())
                .hra(s.getHra())
                .da(s.getDa())
                .pf(s.getPf())
                .allowances(s.getAllowances())
                .effectiveFrom(s.getEffectiveFrom())
                .effectiveTo(s.getEffectiveTo())
                .build();
    }

    // === Request -> Entity ===

    public static SalaryStructure toEntity(SalaryStructureUpsertRequest r, Organization org, Employee emp) {
        if (r == null || org == null || emp == null) return null;
        SalaryStructure s = new SalaryStructure();
        s.setOrganization(org);
        s.setEmployee(emp);
        s.setBasic(r.getBasic());
        s.setHra(r.getHra());
        s.setDa(r.getDa());
        s.setPf(r.getPf());
        s.setAllowances(r.getAllowances());
        s.setEffectiveFrom(r.getEffectiveFrom());
        return s;
    }

    public static SalaryStructure apply(SalaryStructureUpsertRequest r, SalaryStructure target) {
        if (r == null || target == null) return target;
        target.setBasic(r.getBasic());
        target.setHra(r.getHra());
        target.setDa(r.getDa());
        target.setPf(r.getPf());
        target.setAllowances(r.getAllowances());
        target.setEffectiveFrom(r.getEffectiveFrom());
        return target;
    }
}
