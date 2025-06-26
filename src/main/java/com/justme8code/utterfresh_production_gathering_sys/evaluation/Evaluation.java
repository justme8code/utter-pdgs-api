package com.justme8code.utterfresh_production_gathering_sys.evaluation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Evaluation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String batchRange;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @ManyToOne // WAS @OneToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column
    private LocalDate manufacturedDate;

    @Column
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;

    @OneToMany(mappedBy = "evaluation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude // You correctly added this, which solves the toString() loop in this direction.
    @JsonManagedReference // <--- SOLUTION: This is the "forward" part of the reference for JSON
    private List<ProductionEvaluation> productionEvaluations;

}
