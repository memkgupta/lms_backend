package com.lms.organisation_service.utils.specifications.implementations;

import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.utils.specifications.EmployeeSpecification;
import com.lms.organisation_service.utils.specifications.SpecificationStrategy;
import com.lms.organisation_service.utils.specifications.generics.SpecificationStrategyFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmployeeSpecificationStrategyFactory extends SpecificationStrategyFactory<Employee> {
    public EmployeeSpecificationStrategyFactory(List<SpecificationStrategy<Employee>> specificationStrategies) {
        super(specificationStrategies);
    }
}
