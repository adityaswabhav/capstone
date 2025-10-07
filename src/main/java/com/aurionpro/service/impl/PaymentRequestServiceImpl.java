package com.aurionpro.service.impl;

import com.aurionpro.dto.request.PaymentReqSalarySubmitReq;
import com.aurionpro.dto.request.PaymentReqVendorCreateReq;
import com.aurionpro.entity.finance.BusinessParty;
import com.aurionpro.entity.finance.PaymentRequest;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.mapper.PaymentRequestMapper;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.finance.BusinessPartyRepository;
import com.aurionpro.repository.finance.PaymentRequestRepository;
import com.aurionpro.repository.payroll.PayrollCycleRepo;
import com.aurionpro.service.PaymentRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentRequestServiceImpl implements PaymentRequestService {

    private final OrganizationRepository organizationRepository;
    private final BusinessPartyRepository businessPartyRepository;
    private final PayrollCycleRepo payrollCycleRepository;
    private final PaymentRequestRepository paymentRequestRepository;

    @Override
    @Transactional
    public PaymentRequest createVendorRequestEntity(PaymentReqVendorCreateReq request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        BusinessParty party = businessPartyRepository.findById(request.getBusinessPartyId())
                .orElseThrow(() -> new EntityNotFoundException("Business party not found"));
        if (!party.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Party not in organization");

        PaymentRequest pr = PaymentRequestMapper.fromVendor(org, party, request);
        return paymentRequestRepository.save(pr);
    }

    @Override
    @Transactional
    public PaymentRequest submitSalaryRequestEntity(PaymentReqSalarySubmitReq request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        PayrollCycle cycle = payrollCycleRepository.findById(request.getPayrollCycleId())
                .orElseThrow(() -> new EntityNotFoundException("Payroll cycle not found"));
        if (!cycle.getOrganization().getId().equals(org.getId()))
            throw new IllegalArgumentException("Cycle not in organization");

        PaymentRequest pr = PaymentRequestMapper.fromSalary(org, cycle, request);
        return paymentRequestRepository.save(pr);
    }

    @Override
    public List<PaymentRequest> findAll(Long orgId, String status) {
        if (status != null && !status.isBlank()) {
            return paymentRequestRepository.findByOrganizationIdAndStatus(orgId, status);
        }
        return paymentRequestRepository.findByOrganizationId(orgId);
    }
}
