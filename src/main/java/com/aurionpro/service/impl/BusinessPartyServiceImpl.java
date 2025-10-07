package com.aurionpro.service.impl;

import com.aurionpro.dto.request.BusinessPartyCreateRequest;
import com.aurionpro.entity.finance.BusinessParty;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.entity.ref.RefBusinessPartyType;
import com.aurionpro.mapper.BusinessPartyMapper;
import com.aurionpro.repository.OrganizationRepository;
import com.aurionpro.repository.finance.BusinessPartyRepository;
import com.aurionpro.repository.ref.RefBusinessPartyTypeRepo;
import com.aurionpro.service.BusinessPartyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessPartyServiceImpl implements BusinessPartyService {

    private final OrganizationRepository organizationRepository;
    private final BusinessPartyRepository businessPartyRepository;
    private final RefBusinessPartyTypeRepo refBusinessPartyTypeRepository;

    @Override
    @Transactional
    public BusinessParty createEntity(BusinessPartyCreateRequest request) {
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        RefBusinessPartyType type = refBusinessPartyTypeRepository.findByCodeAndActiveTrue(request.getTypeCode())
                .orElseThrow(() -> new EntityNotFoundException("Invalid party type"));
        BusinessParty b = BusinessPartyMapper.toEntity(request, org, type);
        return businessPartyRepository.save(b);
    }

    @Override
    public List<BusinessParty> findAll(Long orgId, String typeCode) {
        if (typeCode != null && !typeCode.isBlank()) {
            return businessPartyRepository.findByOrganizationIdAndType_Code(orgId, typeCode);
        }
        return businessPartyRepository.findByOrganizationId(orgId);
    }
}
