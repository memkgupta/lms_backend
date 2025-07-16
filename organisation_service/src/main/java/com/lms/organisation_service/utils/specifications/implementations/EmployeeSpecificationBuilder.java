package com.lms.organisation_service.utils.specifications.implementations;

import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.utils.specifications.generics.SpecificationBuilder;
import com.lms.organisation_service.utils.specifications.generics.SpecificationStrategyFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSpecificationBuilder extends SpecificationBuilder<Employee> {
    public EmployeeSpecificationBuilder(SpecificationStrategyFactory<Employee> specificationStrategyFactory) {
        super(specificationStrategyFactory);
    }
}
