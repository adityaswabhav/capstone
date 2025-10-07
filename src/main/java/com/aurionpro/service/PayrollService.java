package com.aurionpro.service;

import com.aurionpro.dto.request.PayrollCycleCreateRequest;
import com.aurionpro.dto.request.PayrollCycleLockRequest;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.entity.payroll.PayrollLine;

import java.util.List;

public interface PayrollService {
    PayrollCycle createCycleEntity(PayrollCycleCreateRequest request);
    PayrollCycle lockCycle(Long orgId, PayrollCycleLockRequest request);
    List<PayrollCycle> findCycles(Long orgId, Integer month, Integer year);
    List<PayrollLine> findLines(Long orgId, Long cycleId);
}
