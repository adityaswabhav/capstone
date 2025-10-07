package com.aurionpro.controller.org;

import com.aurionpro.dto.request.DepartmentCreateRequest;
import com.aurionpro.dto.request.DepartmentUpdateRequest;
import com.aurionpro.dto.response.DepartmentResponse;
import com.aurionpro.entity.org.Department;
import com.aurionpro.mapper.DepartmentMapper;
import com.aurionpro.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(@PathVariable Long orgId, @RequestBody @Valid DepartmentCreateRequest request) {
        request.setOrganizationId(orgId);
        Department saved = departmentService.createEntity(request);
        return DepartmentMapper.toResponse(saved);
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(@PathVariable Long orgId, @PathVariable Long id,
                                     @RequestBody @Valid DepartmentUpdateRequest request) {
        request.setOrganizationId(orgId);
        request.setId(id);
        Department updated = departmentService.updateEntity(request);
        return DepartmentMapper.toResponse(updated);
    }

    @GetMapping
    public List<DepartmentResponse> list(@PathVariable Long orgId) {
        List<Department> list = departmentService.findAllByOrg(orgId);
        return DepartmentMapper.toResponseList(list);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long orgId, @PathVariable Long id) {
        departmentService.delete(orgId, id);
    }
}
