package com.lms.organisation_service.utils.specifications;

import com.lms.organisation_service.annotations.SpecificationKey;
import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.entities.Organisation;
import com.lms.organisation_service.entities.Role;
import jakarta.persistence.criteria.Join;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Configuration
public class EmployeeSpecification {

    @Component
    @SpecificationKey("emp_firstName")
    public static class FirstNameSpecification implements SpecificationStrategy<Employee> {
        @Override
        public Specification<Employee> getSpecification(String value) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + value.toLowerCase() + "%");
        }
    }

    @Component
    @SpecificationKey("emp_lastName")
    public static class LastNameSpecification implements SpecificationStrategy<Employee> {
        @Override
        public Specification<Employee> getSpecification(String value) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + value.toLowerCase() + "%");
        }
    }

    @Component
    @SpecificationKey("emp_email")
    public static class EmailSpecification implements SpecificationStrategy<Employee> {
        @Override
        public Specification<Employee> getSpecification(String value) {
            return (root, query, cb) -> cb.equal(cb.lower(root.get("email")), value.toLowerCase());
        }
    }

    @Component
    @SpecificationKey("emp_phone")
    public static class PhoneSpecification implements SpecificationStrategy<Employee> {
        @Override
        public Specification<Employee> getSpecification(String value) {
            return (root, query, cb) -> cb.equal(root.get("phone"), value);
        }
    }

    @Component
    @SpecificationKey("emp_role")
    public static class RoleSpecification implements SpecificationStrategy<Employee> {
        @Override
        public Specification<Employee> getSpecification(String value) {
            try {
                Role role = Role.valueOf(value.toUpperCase());
                return (root, query, cb) -> cb.equal(root.get("role"), role);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    @Component
    @SpecificationKey("emp_organisationId")
    public static class OrganisationIdSpecification implements SpecificationStrategy<Employee> {
        @Override
        public Specification<Employee> getSpecification(String value) {
            try {
                Long orgId = Long.parseLong(value);
                return (root, query, cb) -> {
                    Join<Employee, Organisation> orgJoin = root.join("organisation");
                    return cb.equal(orgJoin.get("id"), orgId);
                };
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
