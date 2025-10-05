package com.aurionpro.mapper;

import com.aurionpro.dto.request.EmployeeCreateRequest;
import com.aurionpro.dto.request.EmployeeUpdateRequest;
import com.aurionpro.dto.response.EmployeeResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.entity.org.Department;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.security.User;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    private EmployeeMapper() {}

    // === Entity -> Response ===

    public static EmployeeResponse toResponse(Employee e) {
        if (e == null) return null;
        return EmployeeResponse.builder()
                .id(e.getId())
                .organizationId(e.getOrganization() != null ? e.getOrganization().getId() : null)
                .code(e.getCode())
                .fullName(e.getUser() != null ? e.getUser().getFullName() : null)
                .email(e.getUser() != null ? e.getUser().getEmail() : null)
                .phone(e.getUser() != null ? e.getUser().getPhone() : null)
                .departmentId(e.getDepartment() != null ? e.getDepartment().getId() : null)
                .departmentName(e.getDepartment() != null ? e.getDepartment().getName() : null)
                .inPayroll(Boolean.TRUE.equals(e.getInPayroll()))
                .build();
    }

    public static List<EmployeeResponse> toResponseList(List<Employee> list) {
        if (list == null) return null;
        return list.stream().map(EmployeeMapper::toResponse).collect(Collectors.toList());
    }

    // === Request -> Entity ===

    public static Employee toEntity(EmployeeCreateRequest r, Organization org, Department dept, User user) {
        if (r == null || org == null || user == null) return null;
        Employee e = new Employee();
        e.setOrganization(org);
        e.setCode(r.getCode());
        e.setDepartment(dept);
        e.setUser(user);
        e.setInPayroll(false);
        return e;
    }

    public static Employee toEntity(EmployeeUpdateRequest r, Organization org, Department dept, Employee target) {
        if (r == null || org == null || target == null) return null;
        target.setOrganization(org);
        target.setDepartment(dept);
        if (r.getInPayroll() != null) target.setInPayroll(r.getInPayroll());
        // name/email/phone changes belong to User; handle in service layer
        return target;
    }
}
