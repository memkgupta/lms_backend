package com.lms.organisation_service.utils.specifications.generics;

import com.lms.organisation_service.annotations.SpecificationKey;
import com.lms.organisation_service.utils.specifications.SpecificationStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public abstract class SpecificationStrategyFactory<T> {
    protected final Map<String, SpecificationStrategy<T>> strategies;

    public SpecificationStrategyFactory(List<SpecificationStrategy<T>> strategyList) {
        this.strategies = strategyList.stream().collect(Collectors.toMap(
                strategy -> {
                    SpecificationKey key = strategy.getClass().getAnnotation(SpecificationKey.class);
                    if (key == null) throw new IllegalArgumentException("Missing @SpecificationKey");
                    return key.value();
                },
                Function.identity()
        ));
    }

    public Specification<T> getSpecification(String key, String value) {
        SpecificationStrategy<T> strategy = strategies.get(key);
        return strategy != null ? strategy.getSpecification(value) : null;
    }
}
