package com.aurionpro.controller.org;

import com.aurionpro.dto.request.EmployeeDocumentReviewRequest;
import com.aurionpro.dto.request.EmployeeDocumentUploadRequest;
import com.aurionpro.dto.response.EmployeeDocumentResponse;
import com.aurionpro.entity.employee.EmployeeDocument;
import com.aurionpro.mapper.EmployeeDocumentMapper;
import com.aurionpro.service.EmployeeDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/employees/{employeeId}/documents")
@RequiredArgsConstructor
public class EmployeeDocumentController {

    private final EmployeeDocumentService employeeDocumentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDocumentResponse upload(@PathVariable Long orgId,
                                           @PathVariable Long employeeId,
                                           @RequestBody @Valid EmployeeDocumentUploadRequest request) {
        request.setEmployeeId(employeeId);
        EmployeeDocument saved = employeeDocumentService.uploadEntity(orgId, request);
        return EmployeeDocumentMapper.toResponse(saved);
    }

    @PostMapping("/{docId}/review")
    public EmployeeDocumentResponse review(@PathVariable Long orgId,
                                           @PathVariable Long employeeId,
                                           @PathVariable Long docId,
                                           @RequestBody @Valid EmployeeDocumentReviewRequest request) {
        EmployeeDocument updated = employeeDocumentService.review(orgId, employeeId, docId, request);
        return EmployeeDocumentMapper.toResponse(updated);
    }

    @GetMapping
    public List<EmployeeDocumentResponse> list(@PathVariable Long orgId,
                                               @PathVariable Long employeeId) {
        List<EmployeeDocument> list = employeeDocumentService.findAll(orgId, employeeId);
        return EmployeeDocumentMapper.toResponseList(list);
    }
}
