package com.aurionpro.mapper;

import com.aurionpro.dto.request.DepartmentCreateRequest;
import com.aurionpro.dto.request.DepartmentUpdateRequest;
import com.aurionpro.dto.response.DepartmentResponse;
import com.aurionpro.entity.org.Department;
import com.aurionpro.entity.org.Organization;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

    private DepartmentMapper() {}

    // === Entity -> Response ===

    public static DepartmentResponse toResponse(Department d) {
        if (d == null) return null;
        return DepartmentResponse.builder()
                .id(d.getId())
                .organizationId(d.getOrganization() != null ? d.getOrganization().getId() : null)
                .name(d.getName())
                .build();
    }

    public static List<DepartmentResponse> toResponseList(List<Department> list) {
        if (list == null) return null;
        return list.stream().map(DepartmentMapper::toResponse).collect(Collectors.toList());
    }

    // === Request -> Entity ===

    public static Department toEntity(DepartmentCreateRequest r, Organization org) {
        if (r == null || org == null) return null;
        Department d = new Department();
        d.setOrganization(org);
        d.setName(r.getName());
        return d;
    }

    public static Department toEntity(DepartmentUpdateRequest r, Organization org, Department target) {
        if (r == null || org == null || target == null) return null;
        target.setOrganization(org);
        target.setName(r.getName());
        return target;
    }
}
