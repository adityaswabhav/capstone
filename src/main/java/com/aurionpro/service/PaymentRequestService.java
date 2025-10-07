package com.aurionpro.service;

import com.aurionpro.dto.request.PaymentReqSalarySubmitReq;
import com.aurionpro.dto.request.PaymentReqVendorCreateReq;
import com.aurionpro.entity.finance.PaymentRequest;

import java.util.List;

public interface PaymentRequestService {
    PaymentRequest createVendorRequestEntity(PaymentReqVendorCreateReq request);
    PaymentRequest submitSalaryRequestEntity(PaymentReqSalarySubmitReq request);
    List<PaymentRequest> findAll(Long orgId, String status);
}
