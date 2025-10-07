package com.aurionpro.controller.org;

import com.aurionpro.dto.request.OrgRegistrationRequest;
import com.aurionpro.dto.response.OrganizationResponse;
import com.aurionpro.entity.org.Organization;
import com.aurionpro.mapper.OrganizationMapper;
import com.aurionpro.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/org/registrations")
@RequiredArgsConstructor
public class OrganizationOnboardingController {

    private final OrganizationService organizationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationResponse register(@RequestBody @Valid OrgRegistrationRequest request) {
        Organization saved = organizationService.registerEntity(request);
        return OrganizationMapper.toResponse(saved);
    }
}
