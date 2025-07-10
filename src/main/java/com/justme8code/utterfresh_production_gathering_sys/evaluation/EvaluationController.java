package com.justme8code.utterfresh_production_gathering_sys.evaluation;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.dto.EvaluationDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.dto.EvaluationInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // create
    /*@PostMapping
    public ResponseEntity<Void> createEvaluation(@RequestBody EvaluationPayload evPayload) {
        evaluationService.createEvaluation(evPayload);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/{evaluationId}")
    public ResponseEntity<EvaluationInfoDto> getEvaluation(@PathVariable("evaluationId") Long evaluationId) {
        return new ResponseEntity<>(evaluationService.getEvaluation(evaluationId), HttpStatus.OK);
    }

    // get
    @GetMapping
    public ResponseEntity<List<EvaluationDto>> getEvaluations() {
        return new ResponseEntity<>(evaluationService.getEvaluations(), HttpStatus.OK);
    }

}
