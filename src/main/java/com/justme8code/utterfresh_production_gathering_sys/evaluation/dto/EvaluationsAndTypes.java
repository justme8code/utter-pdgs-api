package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvaluationsAndTypes {
    List<EvaluationDto> evaluations;
    List<String> evaluationTypes;
}
