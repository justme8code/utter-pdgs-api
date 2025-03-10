package com.justme8code.utterfresh_production_gathering_sys.repository.specifications;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import org.springframework.data.jpa.domain.Specification;

public class ProductionSpecifications {

    public static Specification<Production> hasProductionName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("production"), "%" + name + "%");
    }

    public static Specification<Production> hasStartDate(String startDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("startDate"), startDate);
    }
}