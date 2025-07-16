package com.lms.organisation_service.controllers;

import com.lms.organisation_service.dtos.PaginatedResponse;
import com.lms.organisation_service.dtos.employee.EmployeeDTO;
import com.lms.organisation_service.dtos.employee.EmployeeRequestDTO;
import com.lms.organisation_service.dtos.employee.EmployeeResponseDTO;
import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.services.EmployeeService;

import com.lms.organisation_service.utils.PaginationUtil;
import com.lms.organisation_service.utils.specifications.implementations.EmployeeSpecificationStrategyFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeSpecificationStrategyFactory specFactory;

    // ✅ Create new employee
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequestDTO employeeDTO) {

        return employeeService.createEmployee(employeeDTO);
    }

    // ✅ Get employee by ID
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        Employee emp = employeeService.getEmployeeById(id);
        return EmployeeResponseDTO.builder()
                .id(emp.getId())
                .role(emp.getRole())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .email(emp.getEmail())
                .phone(emp.getPhone())
                .build();
    }

    // ✅ Update employee
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee emp = employeeService.updateEmployee(id, updatedEmployee);
        return EmployeeResponseDTO.builder()
                .id(emp.getId())
                .role(emp.getRole())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .email(emp.getEmail())
                .phone(emp.getPhone())
                .build();
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }




    @GetMapping
    public ResponseEntity<PaginatedResponse<Employee>> getEmployees(@RequestParam Map<String, String> filters) {
        Specification<Employee> spec = null;
        int page = Integer.parseInt(filters.get("page"));
        int size = Integer.parseInt(filters.get("size"));
        filters.remove("page");
        filters.remove("size");
        Pageable pageable = PageRequest.of(page, size);
        for (var entry : filters.entrySet()) {
            Specification<Employee> current = specFactory.getSpecification(entry.getKey(), entry.getValue());
            if (current != null) {
                spec = (spec == null) ? current : spec.and(current);
            }
        }

        Page<Employee> employees = employeeService.getAllEmployees(pageable,spec);
        PaginatedResponse<Employee> paginatedResponse = PaginationUtil.toPaginatedResponse(employees);
        return ResponseEntity.ok(paginatedResponse);
    }


}
