package com.justme8code.utterfresh_production_gathering_sys.models.event;

import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseTransfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👇 The original leftover Purchase being transferred
    @ManyToOne(optional = false)
    private Purchase purchase;

    // 👇 The production where this came from
    @ManyToOne(optional = false)
    private Production fromProduction;

    // 👇 Where it's going (optional at first)
    @ManyToOne
    private Production toProduction;

    // 👇 This is the new Purchase created in `toProduction`
    @OneToOne
    private Purchase resultingPurchase;

    private boolean transferred;

    private LocalDateTime transferredAt;

    private String transferNotes;

    public void assignToProduction(Production targetProduction) {
        if (!transferred) {
            throw new IllegalStateException("This transfer is not marked as transferable.");
        }
        if (this.toProduction != null) {
            throw new IllegalStateException("Transfer target already assigned.");
        }
        this.toProduction = targetProduction;
        this.transferredAt = LocalDateTime.now();
    }
}
