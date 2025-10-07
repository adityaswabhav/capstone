package com.aurionpro.controller.bank;

import com.aurionpro.dto.request.OrgApprovalRequest;
import com.aurionpro.dto.response.OrganizationResponse;
import com.aurionpro.entity.enums.OrganizationStatus;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.mapper.OrganizationMapper;
import com.aurionpro.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/org")
@RequiredArgsConstructor
public class BankAdminOrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/pending")
    public List<OrganizationResponse> listPending() {
        List<Organization> list = organizationService.findByStatus(OrganizationStatus.PENDING);
        return OrganizationMapper.toResponseList(list);
    }

    @PostMapping("/{orgId}/decision")
    public OrganizationResponse decide(@PathVariable Long orgId, @RequestBody @Valid OrgApprovalRequest request) {
        Organization updated = organizationService.decide(orgId, request);
        return OrganizationMapper.toResponse(updated);
    }
}
