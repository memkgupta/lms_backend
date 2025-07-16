package com.lms.organisation_service.repositories;

import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.entities.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> , JpaSpecificationExecutor<Employee> {
    List<Employee> findAllByOrganisationId(Long organisationId, Specification<Employee> spec);
    List<Employee> findAllByRole(Role role, Specification<Employee> spec);
}
