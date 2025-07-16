package com.lms.organisation_service.utils.specifications;

import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface SpecificationStrategy<T> {
    Specification<T> getSpecification(String value);
}
