package com.lms.organisation_service.utils.specifications.implementations;

import com.lms.organisation_service.entities.Organisation;
import com.lms.organisation_service.utils.specifications.generics.SpecificationBuilder;
import com.lms.organisation_service.utils.specifications.generics.SpecificationStrategyFactory;

public class JobSpecificationBuilder extends SpecificationBuilder<Organisation> {
    public JobSpecificationBuilder(SpecificationStrategyFactory<Organisation> specificationStrategyFactory) {
        super(specificationStrategyFactory);
    }
}
