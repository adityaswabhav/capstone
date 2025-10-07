package com.aurionpro.controller.org;

import com.aurionpro.dto.request.BankAccountUpdateCreateRequest;
import com.aurionpro.dto.request.EmployeeDocumentReviewRequest;
import com.aurionpro.dto.response.BankAccountUpdateResponse;
import com.aurionpro.entity.employee.BankAccountUpdateRequest;
import com.aurionpro.mapper.BankAccountUpdateMapper;
import com.aurionpro.service.BankAccountUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/org/{orgId}/employees/{employeeId}/bank-updates")
@RequiredArgsConstructor
public class BankAccountUpdateController {

    private final BankAccountUpdateService bankAccountUpdateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountUpdateResponse create(@PathVariable Long orgId,
                                            @PathVariable Long employeeId,
                                            @RequestBody @Valid BankAccountUpdateCreateRequest request) {
        request.setEmployeeId(employeeId);
        BankAccountUpdateRequest saved = bankAccountUpdateService.createEntity(orgId, request);
        return BankAccountUpdateMapper.toResponse(saved);
    }

    @PostMapping("/{id}/review")
    public BankAccountUpdateResponse review(@PathVariable Long orgId,
                                            @PathVariable Long employeeId,
                                            @PathVariable Long id,
                                            @RequestBody @Valid EmployeeDocumentReviewRequest request) {
        BankAccountUpdateRequest updated = bankAccountUpdateService.review(orgId, employeeId, id, request);
        return BankAccountUpdateMapper.toResponse(updated);
    }
}
