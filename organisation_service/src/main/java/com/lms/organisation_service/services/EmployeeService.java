package com.lms.organisation_service.services;

import com.lms.organisation_service.dtos.employee.EmployeeRequestDTO;
import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.entities.Organisation;
import com.lms.organisation_service.entities.Role;
import com.lms.organisation_service.exceptions.BadRequestException;
import com.lms.organisation_service.exceptions.EntityNotFound;
import com.lms.organisation_service.repositories.EmployeeRepository;
import com.lms.organisation_service.repositories.OrganisationRepository;
import com.lms.organisation_service.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final Set<String> ALLOWED_UPDATE_FIELDS = Set.of(
            "firstName",
            "lastName",
            "email",
            "phone",
            "role"
    );
    private final EmployeeRepository employeeRepository;

    private final OrganisationRepository organisationRepository;

    public Employee createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setRole(employeeRequestDTO.getRole());
        Organisation organisation = organisationRepository.findById(employeeRequestDTO.getOrganisationId()).orElse(null);
        if(organisation == null) {
            throw new BadRequestException("Invalid organisation");
        }
        employee.setOrganisation(organisation);
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Long id, Employee updatedData) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Employee", "id", String.valueOf(id)));


        String[] updateFields = Utils.getNullPropertyNames(updatedData);
        Arrays.stream(updateFields)
                        .forEach(field->{
                            if(!ALLOWED_UPDATE_FIELDS.contains(field))
                            {
                                throw new BadRequestException(
                                        "Updating "+field+" field is not allowed for the employee"
                                );
                            }
                        });
        BeanUtils.copyProperties(updatedData, existing, updateFields);
        return employeeRepository.save(existing);
    }


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Employee", "id", String.valueOf(id)));
    }


    public Page<Employee> getAllEmployees(Pageable pageable, Specification<Employee> spec) {

        return employeeRepository.findAll(spec,pageable);
    }


    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFound("Employee", "id", String.valueOf(id));
        }
        //TODO: revoking permissions

        employeeRepository.deleteById(id);
    }



    public Employee makeAdmin(Long orgId , Long empId)
    {
        Employee employee = employeeRepository.findById(empId).orElse(null);
        if(employee == null) {
            throw new EntityNotFound("Employee", "id", String.valueOf(empId));
        }
        Organisation organisation = organisationRepository.findById(orgId).orElse(null);
        if(organisation==null)
        {
            throw new BadRequestException("Invalid organisation");
        }

        employee.setRole(Role.ADMIN);
        return employeeRepository.save(employee);

        //todo call to function to update permissions

    }
}
