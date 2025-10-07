package com.aurionpro.controller.support;

import com.aurionpro.dto.request.ConcernCreateRequest;
import com.aurionpro.dto.request.ConcernResolveRequest;
import com.aurionpro.dto.response.ConcernResponse;
import com.aurionpro.entity.support.Concern;
import com.aurionpro.mapper.ConcernMapper;
import com.aurionpro.service.ConcernService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/concerns")
@RequiredArgsConstructor
public class ConcernController {

    private final ConcernService concernService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConcernResponse create(@PathVariable Long orgId, @RequestBody @Valid ConcernCreateRequest request) {
        request.setOrganizationId(orgId);
        Concern saved = concernService.createEntity(request);
        return ConcernMapper.toResponse(saved);
    }

    @GetMapping
    public List<ConcernResponse> list(@PathVariable Long orgId,
                                      @RequestParam(required = false) Long employeeId,
                                      @RequestParam(required = false) String status) {
        List<Concern> list = concernService.findAll(orgId, employeeId, status);
        return list.stream().map(ConcernMapper::toResponse).toList();
    }

    @PostMapping("/{id}/resolve")
    public ConcernResponse resolve(@PathVariable Long orgId, @PathVariable Long id,
                                   @RequestBody @Valid ConcernResolveRequest request) {
        Concern updated = concernService.resolve(orgId, id, request);
        return ConcernMapper.toResponse(updated);
    }
}
