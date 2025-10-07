package com.aurionpro.controller.finance;

import com.aurionpro.dto.request.BusinessPartyCreateRequest;
import com.aurionpro.dto.response.BusinessPartyResponse;
import com.aurionpro.entity.finance.BusinessParty;
import com.aurionpro.mapper.BusinessPartyMapper;
import com.aurionpro.service.BusinessPartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/parties")
@RequiredArgsConstructor
public class BusinessPartyController {

    private final BusinessPartyService businessPartyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessPartyResponse create(@PathVariable Long orgId,
                                        @RequestBody @Valid BusinessPartyCreateRequest request) {
        request.setOrganizationId(orgId);
        BusinessParty saved = businessPartyService.createEntity(request);
        return BusinessPartyMapper.toResponse(saved);
    }

    @GetMapping
    public List<BusinessPartyResponse> list(@PathVariable Long orgId,
                                            @RequestParam(required = false) String typeCode) {
        List<BusinessParty> list = businessPartyService.findAll(orgId, typeCode);
        return BusinessPartyMapper.toResponseList(list);
    }
}
