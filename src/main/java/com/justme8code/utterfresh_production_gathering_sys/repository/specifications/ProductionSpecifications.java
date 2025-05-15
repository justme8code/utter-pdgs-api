package com.justme8code.utterfresh_production_gathering_sys.repository.specifications;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ProductionSpecifications {

    public static Specification<Production> hasProductionName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction(); // no-op if filter is empty
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Production> hasStartDate(LocalDate startDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null) {
                return criteriaBuilder.conjunction(); // same thing, no-op
            }
            return criteriaBuilder.equal(root.get("startDate"), startDate);
        };
    }
}
