package com.aurionpro.service;

import com.aurionpro.dto.request.OrgApprovalRequest;
import com.aurionpro.entity.finance.PaymentRequest;

import java.util.List;

public interface BankPaymentReviewService {
    List<PaymentRequest> findSubmitted();
    PaymentRequest decide(Long paymentRequestId, OrgApprovalRequest request);
}
