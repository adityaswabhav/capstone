package com.aurionpro.service;

import com.aurionpro.dto.request.BusinessPartyCreateRequest;
import com.aurionpro.entity.finance.BusinessParty;

import java.util.List;

public interface BusinessPartyService {
    BusinessParty createEntity(BusinessPartyCreateRequest request);
    List<BusinessParty> findAll(Long orgId, String typeCode);
}
