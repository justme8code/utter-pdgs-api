package com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductMixDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductMixMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.ProductMixOverview;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Product;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductMixRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.dashboard.ProductMixOverviewService;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Setter
public class ProductMixOverviewServiceImpl implements ProductMixOverviewService {
    private final ProductMixRepository productMixRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductMixMapper productMixMapper;

    public ProductMixOverviewServiceImpl(ProductMixRepository productMixRepository, ProductRepository productRepository, ProductMapper productMapper, ProductMixMapper productMixMapper) {
        this.productMixRepository = productMixRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productMixMapper = productMixMapper;
    }

    @Override
    public long totalProductsProduced() {
        return productMixRepository.count();
    }

    @Override
    public List<ProductDashboardData> topProductsByMixCount() {
        List<Product> p = productRepository.findAll();
        return p.stream()
                .sorted(Comparator.comparing(Product::getTotalProductMixCount))
                .map(productMapper::toDto1)
                .toList();
    }

    @Override
    public List<ProductMixDashboardData> recentProductMixes() {
        Pageable top5 = PageRequest.of(0, 5);
        return productMixRepository
                .findTop5ProductMixCreatedAtBetween(
                        LocalDateTime.now().minusDays(30), LocalDateTime.now(), top5)
                .stream().map(productMixMapper::toDto1).toList();
    }

    @Override
    public ProductMixOverview getProductMixOverview() {
        return new ProductMixOverview(totalProductsProduced(), topProductsByMixCount(), recentProductMixes());
    }
}
