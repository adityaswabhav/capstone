package com.aurionpro.controller.bank;

import com.aurionpro.dto.request.OrgApprovalRequest;
import com.aurionpro.dto.response.PaymentRequestResponse;
import com.aurionpro.entity.finance.PaymentRequest;
import com.aurionpro.mapper.PaymentRequestMapper;
import com.aurionpro.service.BankPaymentReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/payments")
@RequiredArgsConstructor
public class BankAdminPaymentController {

    private final BankPaymentReviewService bankPaymentReviewService;

    @GetMapping("/submitted")
    public List<PaymentRequestResponse> listSubmitted() {
        List<PaymentRequest> list = bankPaymentReviewService.findSubmitted();
        return list.stream().map(PaymentRequestMapper::toResponse).toList();
    }

    @PostMapping("/{paymentRequestId}/decision")
    public PaymentRequestResponse decide(@PathVariable Long paymentRequestId,
                                         @RequestBody @Valid OrgApprovalRequest request) {
        PaymentRequest updated = bankPaymentReviewService.decide(paymentRequestId, request);
        return PaymentRequestMapper.toResponse(updated);
    }
}
