package com.aurionpro.controller.finance;

import com.aurionpro.dto.request.PaymentRequestSalarySubmitRequest;
import com.aurionpro.dto.request.PaymentRequestVendorCreateRequest;
import com.aurionpro.dto.response.PaymentRequestResponse;
import com.aurionpro.entity.finance.PaymentRequest;
import com.aurionpro.mapper.PaymentRequestMapper;
import com.aurionpro.service.PaymentRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/payments")
@RequiredArgsConstructor
public class PaymentRequestController {

    private final PaymentRequestService paymentRequestService;

    @PostMapping("/vendor")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentRequestResponse vendor(@PathVariable Long orgId,
                                         @RequestBody @Valid PaymentRequestVendorCreateRequest request) {
        request.setOrganizationId(orgId);
        PaymentRequest saved = paymentRequestService.createVendorRequestEntity(request);
        return PaymentRequestMapper.toResponse(saved);
    }

    @PostMapping("/salary")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentRequestResponse salary(@PathVariable Long orgId,
                                         @RequestBody @Valid PaymentRequestSalarySubmitRequest request) {
        request.setOrganizationId(orgId);
        PaymentRequest saved = paymentRequestService.submitSalaryRequestEntity(request);
        return PaymentRequestMapper.toResponse(saved);
    }

    @GetMapping
    public List<PaymentRequestResponse> list(@PathVariable Long orgId,
                                             @RequestParam(required = false) String status) {
        List<PaymentRequest> list = paymentRequestService.findAll(orgId, status);
        return list.stream().map(PaymentRequestMapper::toResponse).toList();
    }
}
