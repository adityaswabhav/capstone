package com.aurionpro.controller.org;

import com.aurionpro.dto.request.EmployeeCreateRequest;
import com.aurionpro.dto.request.EmployeeUpdateRequest;
import com.aurionpro.dto.response.EmployeeResponse;
import com.aurionpro.entity.employee.Employee;
import com.aurionpro.mapper.EmployeeMapper;
import com.aurionpro.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/{orgId}/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@PathVariable Long orgId, @RequestBody @Valid EmployeeCreateRequest request) {
        request.setOrganizationId(orgId);
        Employee saved = employeeService.createEntity(request);
        return EmployeeMapper.toResponse(saved);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long orgId, @PathVariable Long id,
                                   @RequestBody @Valid EmployeeUpdateRequest request) {
        request.setOrganizationId(orgId);
        request.setId(id);
        Employee updated = employeeService.updateEntity(request);
        return EmployeeMapper.toResponse(updated);
    }

    @GetMapping
    public List<EmployeeResponse> list(@PathVariable Long orgId,
                                       @RequestParam(required = false) Long departmentId) {
        List<Employee> list = employeeService.findAllByOrg(orgId, departmentId);
        return EmployeeMapper.toResponseList(list);
    }

    @GetMapping("/{id}")
    public EmployeeResponse get(@PathVariable Long orgId, @PathVariable Long id) {
        Employee e = employeeService.get(orgId, id);
        return EmployeeMapper.toResponse(e);
    }
}
