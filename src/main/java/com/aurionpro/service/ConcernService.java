package com.aurionpro.service;

import com.aurionpro.dto.request.ConcernCreateRequest;
import com.aurionpro.dto.request.ConcernResolveRequest;
import com.aurionpro.entity.support.Concern;

import java.util.List;

public interface ConcernService {
    Concern createEntity(ConcernCreateRequest request);
    List<Concern> findAll(Long orgId, Long employeeId, String status);
    Concern resolve(Long orgId, Long id, ConcernResolveRequest request);
}
