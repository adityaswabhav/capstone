package com.aurionpro.mapper;

import com.aurionpro.dto.request.PayrollCycleCreateRequest;
import com.aurionpro.dto.response.PayrollCycleResponse;
import com.aurionpro.entity.enums.PayrollStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;

import java.util.List;
import java.util.stream.Collectors;

public class PayrollCycleMapper {

    private PayrollCycleMapper() {}

    // === Entity -> Response ===

    public static PayrollCycleResponse toResponse(PayrollCycle c) {
        if (c == null) return null;
        return PayrollCycleResponse.builder()
                .id(c.getId())
                .organizationId(c.getOrganization() != null ? c.getOrganization().getId() : null)
                .periodMonth(c.getPeriodMonth())
                .periodYear(c.getPeriodYear())
                .status(c.getStatus() != null ? c.getStatus().name() : null)
                .totalNet(c.getTotalNet())
                .build();
    }

    public static List<PayrollCycleResponse> toResponseList(List<PayrollCycle> list) {
        if (list == null) return null;
        return list.stream().map(PayrollCycleMapper::toResponse).collect(Collectors.toList());
    }

    // === Request -> Entity ===

    public static PayrollCycle toEntity(PayrollCycleCreateRequest r, Organization org) {
        if (r == null || org == null) return null;
        PayrollCycle c = new PayrollCycle();
        c.setOrganization(org);
        c.setPeriodMonth(r.getPeriodMonth());
        c.setPeriodYear(r.getPeriodYear());
        c.setStatus(PayrollStatus.DRAFT);
        c.setTotalNet(0L);
        return c;
    }
}
