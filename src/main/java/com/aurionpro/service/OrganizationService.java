package com.aurionpro.service;

import com.aurionpro.dto.request.OrgApprovalRequest;
import com.aurionpro.dto.request.OrgRegistrationRequest;
import com.aurionpro.entity.enums.OrganizationStatus;
import com.aurionpro.entity.org.Organization;

import java.util.List;

public interface OrganizationService {
    Organization registerEntity(OrgRegistrationRequest request);
    List<Organization> findByStatus(OrganizationStatus status);
    Organization decide(Long orgId, OrgApprovalRequest request);
}
