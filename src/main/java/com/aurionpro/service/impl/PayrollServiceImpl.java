package com.aurionpro.service.impl;

import com.aurionpro.dto.request.PayrollCycleCreateRequest;
import com.aurionpro.dto.request.PayrollCycleLockRequest;
import com.aurionpro.entity.enums.PayrollStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.entity.payroll.PayrollLine;
import com.aurionpro.mapper.PayrollCycleMapper;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.payroll.PayrollCycleRepo;
import com.aurionpro.repository.payroll.PayrollLineRepository;
import com.aurionpro.service.PayrollService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final OrganizationRepository organizationRepository;
    private final PayrollCycleRepo payrollCycleRepository;
    private final PayrollLineRepository payrollLineRepository;

    @Override
    @Transactional
    public PayrollCycle createCycleEntity(PayrollCycleCreateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        boolean exists = payrollCycleRepository.existsByOrganizationIdAndPeriodMonthAndPeriodYear(
                org.getId(), request.getPeriodMonth(), request.getPeriodYear());
        if (exists) throw new IllegalArgumentException("Cycle already exists for month/year");
        PayrollCycle cycle = PayrollCycleMapper.toEntity(request, org);
        return payrollCycleRepository.save(cycle);
    }

    @Override
    @Transactional
    public PayrollCycle lockCycle(Long orgId, PayrollCycleLockRequest request) {
        PayrollCycle cycle = payrollCycleRepository.findById(request.getPayrollCycleId())
                .orElseThrow(() -> new EntityNotFoundException("Cycle not found"));
        if (!cycle.getOrganization().getId().equals(orgId))
            throw new IllegalArgumentException("Cycle not in organization");
        if (cycle.getStatus() != PayrollStatus.DRAFT && cycle.getStatus() != PayrollStatus.READY)
            throw new IllegalStateException("Only DRAFT/READY cycles can be locked");
        cycle.setStatus(PayrollStatus.LOCKED);
        return payrollCycleRepository.save(cycle);
    }

    @Override
    public List<PayrollCycle> findCycles(Long orgId, Integer month, Integer year) {
        if (month != null && year != null) {
            return payrollCycleRepository.findByOrganizationIdAndPeriodMonthAndPeriodYear(orgId, month, year);
        }
        return payrollCycleRepository.findByOrganizationId(orgId);
    }

    @Override
    public List<PayrollLine> findLines(Long orgId, Long cycleId) {
        return payrollLineRepository.findByOrganizationIdAndPayrollCycleId(orgId, cycleId);
    }
}
