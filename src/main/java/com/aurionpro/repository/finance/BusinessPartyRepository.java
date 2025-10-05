package com.aurionpro.repository.finance;

import com.aurionpro.entity.finance.BusinessParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessPartyRepository extends JpaRepository<BusinessParty, Long> {
    List<BusinessParty> findByOrganizationId(Long organizationId);
    List<BusinessParty> findByOrganizationIdAndType_Code(Long organizationId, String code);
}
