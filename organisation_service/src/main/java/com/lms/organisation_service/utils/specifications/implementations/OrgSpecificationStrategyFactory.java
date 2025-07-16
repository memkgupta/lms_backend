package com.lms.organisation_service.utils.specifications.implementations;

import com.lms.organisation_service.entities.Organisation;
import com.lms.organisation_service.utils.specifications.SpecificationStrategy;
import com.lms.organisation_service.utils.specifications.generics.SpecificationStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrgSpecificationStrategyFactory extends SpecificationStrategyFactory<Organisation> {

    public OrgSpecificationStrategyFactory(List<SpecificationStrategy<Organisation>> specificationStrategies) {
        super(specificationStrategies);
    }
}
