package com.aurionpro.mapper;

import com.aurionpro.dto.request.PaymentReqSalarySubmitReq;
import com.aurionpro.dto.request.PaymentReqVendorCreateReq;
import com.aurionpro.dto.response.PaymentRequestResponse;
import com.aurionpro.entity.enums.PaymentRequestStatus;
import com.aurionpro.entity.enums.PaymentRequestType;
import com.aurionpro.entity.finance.BusinessParty;
import com.aurionpro.entity.finance.PaymentRequest;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;

public class PaymentRequestMapper {

    private PaymentRequestMapper() {}

    // === Entity -> Response ===

    public static PaymentRequestResponse toResponse(PaymentRequest p) {
        if (p == null) return null;
        return PaymentRequestResponse.builder()
                .id(p.getId())
                .organizationId(p.getOrganization() != null ? p.getOrganization().getId() : null)
                .type(p.getType() != null ? p.getType().name() : null)
                .status(p.getStatus() != null ? p.getStatus().name() : null)
                .currency(p.getCurrency() != null ? p.getCurrency().name() : null)
                .amount(p.getAmount())
                .payrollCycleId(p.getPayrollCycle() != null ? p.getPayrollCycle().getId() : null)
                .businessPartyId(p.getBusinessParty() != null ? p.getBusinessParty().getId() : null)
                .bankReason(p.getBankReason())
                .build();
    }

    // === Requests -> Entity ===

    public static PaymentRequest fromVendor(Organization org, BusinessParty party, PaymentReqVendorCreateReq r) {
        if (org == null || party == null || r == null) return null;
        PaymentRequest p = new PaymentRequest();
        p.setOrganization(org);
        p.setType(PaymentRequestType.VENDOR);
        p.setStatus(PaymentRequestStatus.SUBMITTED);
        p.setCurrency(r.getCurrency());
        p.setAmount(r.getAmount());
        p.setBusinessParty(party);
        p.setIdempotencyKey(r.getIdempotencyKey());
        return p;
    }

    public static PaymentRequest fromSalary(Organization org, PayrollCycle cycle, PaymentReqSalarySubmitReq r) {
        if (org == null || cycle == null || r == null) return null;
        PaymentRequest p = new PaymentRequest();
        p.setOrganization(org);
        p.setType(PaymentRequestType.SALARY);
        p.setStatus(PaymentRequestStatus.SUBMITTED);
        p.setCurrency(r.getCurrency());
        p.setAmount(r.getAmount()); // service may recompute/validate
        p.setPayrollCycle(cycle);
        p.setIdempotencyKey(r.getIdempotencyKey());
        return p;
    }
}
