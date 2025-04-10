package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Product;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ProductMixService {

    ProductMixDto createANewProductMix(ProductMixDto productMixDto);
    ProductMixDto updateThisProductMix(ProductMixDto productMixDto,long productMixId);
    ProductMixDto findThisProductMix(long id);
    List<ProductMixDto> findAllProductMix();
    void deleteThisProductMix(long productMixId);
}
