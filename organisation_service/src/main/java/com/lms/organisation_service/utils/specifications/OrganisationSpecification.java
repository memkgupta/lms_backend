package com.lms.organisation_service.utils.specifications;

import com.lms.organisation_service.annotations.SpecificationKey;
import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.entities.Organisation;
import jakarta.persistence.criteria.Join;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Configuration
public class OrganisationSpecification {

    @Component
    @SpecificationKey("org_name")
    public static class NameSpecification implements SpecificationStrategy<Organisation> {
        @Override
        public Specification<Organisation> getSpecification(String value) {
            return (root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + value.toLowerCase() + "%");
        }
    }

    @Component
    @SpecificationKey("org_orgCode")
    public static class OrgCodeSpecification implements SpecificationStrategy<Organisation> {
        @Override
        public Specification<Organisation> getSpecification(String value) {
            return (root, query, cb) ->
                    cb.equal(root.get("orgCode"), value);
        }
    }

    @Component
    @SpecificationKey("org_location")
    public static class LocationSpecification implements SpecificationStrategy<Organisation> {
        @Override
        public Specification<Organisation> getSpecification(String value) {
            return (root, query, cb) ->
                    cb.like(cb.lower(root.get("location")), "%" + value.toLowerCase() + "%");
        }
    }

    @Component
    @SpecificationKey("org_isVerified")
    public static class IsVerifiedSpecification implements SpecificationStrategy<Organisation> {
        @Override
        public Specification<Organisation> getSpecification(String value) {
            try {
                Boolean boolVal = Boolean.parseBoolean(value);
                return (root, query, cb) ->
                        cb.equal(root.get("isVerified"), boolVal);
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Component
    @SpecificationKey("org_ownerId")
    public static class OwnerIdSpecification implements SpecificationStrategy<Organisation> {
        @Override
        public Specification<Organisation> getSpecification(String value) {
            return (root, query, cb) ->
                    cb.equal(root.get("ownerId"), value);
        }
    }

    @Component
    @SpecificationKey("org_adminId")
    public static class AdminIdSpecification implements SpecificationStrategy<Organisation> {
        @Override
        public Specification<Organisation> getSpecification(String value) {
            try {
                Long adminId = Long.parseLong(value);
                return (root, query, cb) -> {
                    Join<Organisation, Employee> adminJoin = root.join("admin");
                    return cb.equal(adminJoin.get("id"), adminId);
                };
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
