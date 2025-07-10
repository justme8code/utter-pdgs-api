package com.justme8code.utterfresh_production_gathering_sys.services.helpers;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionBatch;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionBatchRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductionBatchHelper {

     public static ProductionBatch getOrCreateActiveBatch(Production p, ProductionBatchRepository pbr) {
        List<ProductionBatch> pbs = productionBatchList(p,pbr);
        return pbs.stream()
                .filter(ProductionBatch::isActive)
                .findFirst()
                .orElseGet(() -> saveProductionBatch(p,pbs.size(),pbr));
    }

    public static ProductionBatch createProductionBatch(Production p, ProductionBatchRepository pbr) {
        List<ProductionBatch> pbs = productionBatchList(p,pbr);
        return saveProductionBatch(p,pbs.size(),pbr);
    }

    // save the production
    private static ProductionBatch saveProductionBatch(Production p,int size, ProductionBatchRepository pbr) {
        List<ProductionBatch> activeBatches = pbr.findProductionBatchByProduction_IdAndActiveIsTrue(p.getId());

        // deactivate batches - defensive programming, incase there were multiple batches with active states
        // which would go against our 1 active batch rule
        for (ProductionBatch oldActiveBatch : activeBatches) {
            oldActiveBatch.setActive(false);
        }
        pbr.saveAll(activeBatches); // Save the deactivation changes


        System.out.println("Creating new batch for production " +  p.getId());
        System.out.println("Existing active batches count: " + activeBatches.size());

        ProductionBatch newBatch = new ProductionBatch();
        newBatch.setName(appendNextBatchName(size));
        newBatch.setActive(true);
        newBatch.setProduction(p);
        return pbr.save(newBatch);
    }

    // kind of an utility class.
    private static String appendNextBatchName(int size){
        return "bn-" + (size + 1);
    }

    // returns a production batch list by production( well supposed to be private but made it public for future use)
    public static List<ProductionBatch> productionBatchList(Production p,ProductionBatchRepository pbr) {
        return pbr
                .findProductionBatchByProduction_Id(p.getId())
                .orElse(new ArrayList<>());
    }
}
