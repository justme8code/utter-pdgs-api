package com.justme8code.utterfresh_production_gathering_sys.evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>, JpaSpecificationExecutor<Evaluation> {
    Optional<Evaluation> findEvaluationById(Long id);

    List<Evaluation> findEvaluationByProduction_Id(Long id);
}