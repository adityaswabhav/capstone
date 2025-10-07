package com.aurionpro.service.impl;

import com.aurionpro.dto.request.OrgApprovalRequest;
import com.aurionpro.entity.enums.PaymentRequestStatus;
import com.aurionpro.entity.finance.PaymentRequest;
import com.aurionpro.repository.finance.PaymentRequestRepository;
import com.aurionpro.service.BankPaymentReviewService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankPaymentReviewServiceImpl implements BankPaymentReviewService {

    private final PaymentRequestRepository paymentRequestRepository;

    @Override
    public List<PaymentRequest> findSubmitted() {
        return paymentRequestRepository.findByStatus(PaymentRequestStatus.SUBMITTED);
    }

    @Override
    @Transactional
    public PaymentRequest decide(Long paymentRequestId, OrgApprovalRequest request) {
        PaymentRequest p = paymentRequestRepository.findById(paymentRequestId)
                .orElseThrow(() -> new EntityNotFoundException("Payment request not found"));
        if (request.isApprove()) {
            p.setStatus(PaymentRequestStatus.APPROVED);
            p.setBankReason(null);
        } else {
            p.setStatus(PaymentRequestStatus.REJECTED);
            p.setBankReason(request.getNote());
        }
        return paymentRequestRepository.save(p);
    }
}
