package com.justme8code.utterfresh_production_gathering_sys.services.interfaces.productmix;

import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixProdStoreDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductionMixService {


    ProductMixProdStoreDto addProductMix(ProductMixDto productMixDto, long prID);

    ProductMixDto updateThisProductMix(ProductMixDto productMixDto, long productMixId);

    ProductMixDto findThisProductMix(long id);

    List<ProductMixDto> findAllProductMix();

    void deleteThisProductMix(long productMixId);

    // New method for paginated retrieval of production mixes
    Page<ProductMixDto> findAllProductMix(Pageable pageable);

    Page<ProductMixDto> findProductMixesBySearch(String search, Pageable pageable);
}
