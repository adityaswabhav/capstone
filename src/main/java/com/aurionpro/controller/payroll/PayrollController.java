package com.aurionpro.controller.payroll;

import com.aurionpro.dto.request.PayrollCycleCreateRequest;
import com.aurionpro.dto.request.PayrollCycleLockRequest;
import com.aurionpro.dto.response.PayrollCycleResponse;
import com.aurionpro.dto.response.PayrollLineResponse;
import com.aurionpro.entity.payroll.PayrollCycle;
import com.aurionpro.entity.payroll.PayrollLine;
import com.aurionpro.mapper.PayrollCycleMapper;
import com.aurionpro.mapper.PayrollLineMapper;
import com.aurionpro.service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    @PostMapping("/cycles")
    @ResponseStatus(HttpStatus.CREATED)
    public PayrollCycleResponse createCycle(@PathVariable Long orgId,
                                            @RequestBody @Valid PayrollCycleCreateRequest request) {
        request.setOrganizationId(orgId);
        PayrollCycle cycle = payrollService.createCycleEntity(request);
        return PayrollCycleMapper.toResponse(cycle);
    }

    @PostMapping("/cycles/{cycleId}/lock")
    public PayrollCycleResponse lockCycle(@PathVariable Long orgId,
                                          @PathVariable Long cycleId,
                                          @RequestBody @Valid PayrollCycleLockRequest request) {
        request.setPayrollCycleId(cycleId);
        PayrollCycle locked = payrollService.lockCycle(orgId, request);
        return PayrollCycleMapper.toResponse(locked);
    }

    @GetMapping("/cycles")
    public List<PayrollCycleResponse> listCycles(@PathVariable Long orgId,
                                                 @RequestParam(required = false) Integer month,
                                                 @RequestParam(required = false) Integer year) {
        List<PayrollCycle> list = payrollService.findCycles(orgId, month, year);
        return list.stream().map(PayrollCycleMapper::toResponse).toList();
    }

    @GetMapping("/cycles/{cycleId}/lines")
    public List<PayrollLineResponse> listLines(@PathVariable Long orgId, @PathVariable Long cycleId) {
        List<PayrollLine> list = payrollService.findLines(orgId, cycleId);
        return list.stream().map(PayrollLineMapper::toResponse).toList();
    }
}
