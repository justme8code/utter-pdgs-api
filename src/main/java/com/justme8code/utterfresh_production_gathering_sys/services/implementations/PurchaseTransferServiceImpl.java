package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ReturnPurchaseAndTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.PurchaseMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.PurchaseTransferMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.PurchaseRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.PurchaseTransferRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.helpers.PurchaseHelper;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.PurchaseTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseTransferServiceImpl implements PurchaseTransferService {

    private final PurchaseTransferRepository purchaseTransferRepository;
    private final PurchaseTransferMapper purchaseTransferMapper;
    private final ProductionRepository productionRepository;
    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;
    private final RawMaterialRepository rawMaterialRepository;
    private final PurchaseRepository purchaseRepository;


    public PurchaseTransferServiceImpl(PurchaseTransferRepository purchaseTransferRepository, PurchaseTransferMapper purchaseTransferMapper, ProductionRepository productionRepository, ProductionRepository productionRepository1, PurchaseService purchaseService,
                                       PurchaseMapper purchaseMapper, RawMaterialRepository rawMaterialRepository, PurchaseRepository purchaseRepository) {
        this.purchaseTransferRepository = purchaseTransferRepository;
        this.purchaseTransferMapper = purchaseTransferMapper;
        this.productionRepository = productionRepository1;
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
        this.rawMaterialRepository = rawMaterialRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<PurchaseTransferDto> getPurchaseTransfersByProductionId(Long productionId) {
        List<PurchaseTransfer> p = purchaseTransferRepository.findByFromProductionId(productionId);
        return p.stream().map(purchaseTransferMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PurchaseTransferDto> getAvailablePurchaseTransfers() {
        return purchaseTransferRepository.findByTransferredIsFalse().stream()
                .map(purchaseTransferMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReturnPurchaseAndTransferDto transferPurchaseToProduction(Long productionId, Long purchaseTransferId) {
        Production production = productionRepository.findProductionById(productionId)
                .orElseThrow(() -> new EntityException("Could not find production", HttpStatus.NOT_FOUND));

        PurchaseTransfer pt = purchaseTransferRepository.findById(purchaseTransferId)
                .orElseThrow(() -> new EntityException("Could not find purchaseTransfer", HttpStatus.NOT_FOUND));

        Purchase p = getPurchase(pt);
        PurchaseHelper.createPurchaseUsage(p,production,p.getRawMaterial(),purchaseMapper);
        p.setTransferred(true);
        Purchase savedPurchase = purchaseRepository.save(p);
        pt.setResultingPurchase(savedPurchase);
        pt.setTransferred(true);
        PurchaseTransfer purchaseTransfer = purchaseTransferRepository.save(pt);

        var r = new ReturnPurchaseAndTransferDto();
        r.setPurchase(purchaseMapper.toDto(savedPurchase));
        r.setPurchaseTransfer(purchaseTransferMapper.toDto(purchaseTransfer));
        return r;
    }

    private static Purchase getPurchase(PurchaseTransfer pt) {
        Purchase fromPurchase = pt.getPurchase();

        Purchase p = new Purchase();
        p.setProductionLostWeight(fromPurchase.getProductionLostWeight());
        p.setUomQty(fromPurchase.getUomQty());
        p.setWeight(fromPurchase.getWeight());
        p.setUsableWeight(fromPurchase.getPurchaseUsage().getUsableWeightLeft());
        p.setCost(fromPurchase.getCost());
        p.setAvgCost(fromPurchase.getAvgCost());
        p.setAvgWeightPerUOM(fromPurchase.getAvgWeightPerUOM());
        p.setRawMaterial(fromPurchase.getRawMaterial());
        p.setSupplier(fromPurchase.getSupplier());
        return p;
    }


}
