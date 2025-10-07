package com.aurionpro.controller.payroll;

import com.aurionpro.dto.response.PayslipResponse;
import com.aurionpro.entity.payroll.Payslip;
import com.aurionpro.mapper.PayslipMapper;
import com.aurionpro.repository.payroll.PayslipRepository;
import com.aurionpro.service.PayslipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/payroll/cycles/{cycleId}/payslips")
@RequiredArgsConstructor
public class PayslipController {

    private final PayslipService payslipService;
    private final PayslipRepository payslipRepository;

    @PostMapping("/employees/{employeeId}/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public PayslipResponse generateForEmployee(@PathVariable Long orgId,
                                               @PathVariable Long cycleId,
                                               @PathVariable Long employeeId) {
        Payslip slip = payslipService.generateForEmployee(orgId, cycleId, employeeId);
        return PayslipMapper.toResponse(slip);
    }

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PayslipResponse> generateForCycle(@PathVariable Long orgId,
                                                  @PathVariable Long cycleId) {
        List<Payslip> slips = payslipService.generateForCycle(orgId, cycleId);
        return slips.stream().map(PayslipMapper::toResponse).toList();
    }

    @GetMapping
    public List<PayslipResponse> list(@PathVariable Long orgId,
                                      @PathVariable Long cycleId) {
        List<Payslip> slips = payslipRepository.findByOrganizationIdAndPayrollCycleId(orgId, cycleId);
        return slips.stream().map(PayslipMapper::toResponse).toList();
    }
}
