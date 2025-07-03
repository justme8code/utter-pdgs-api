package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProdDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionDetailsDto1;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionStore;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T11:41:33+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class ProductionMapperImpl implements ProductionMapper {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private ProductionStoreMapper productionStoreMapper;

    @Override
    public Production toEntity(ProductionDto productionDto) {
        if ( productionDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( productionDto.getId() );
        production.setProductionNumber( productionDto.getProductionNumber() );
        production.setName( productionDto.getName() );
        production.setLastBatch( productionDto.getLastBatch() );
        production.setFinalized( productionDto.isFinalized() );
        production.setStartDate( productionDto.getStartDate() );
        production.setEndDate( productionDto.getEndDate() );
        production.setStaff( staffMapper.toEntity( productionDto.getStaff() ) );
        production.setProductionStore( productionStoreMapper.toEntity( productionDto.getProductionStore() ) );

        linkProductionStore( production );

        return production;
    }

    @Override
    public ProductionDto toDto(Production production) {
        if ( production == null ) {
            return null;
        }

        Long id = null;
        String productionNumber = null;
        String name = null;
        Integer lastBatch = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        StaffDto staff = null;
        boolean finalized = false;
        ProductionStoreDto productionStore = null;

        id = production.getId();
        productionNumber = production.getProductionNumber();
        name = production.getName();
        lastBatch = production.getLastBatch();
        startDate = production.getStartDate();
        endDate = production.getEndDate();
        staff = staffMapper.toDto( production.getStaff() );
        finalized = production.isFinalized();
        productionStore = productionStoreMapper.toDto( production.getProductionStore() );

        ProductionDto productionDto = new ProductionDto( id, productionNumber, name, lastBatch, startDate, endDate, staff, finalized, productionStore );

        return productionDto;
    }

    @Override
    public Production partialUpdate(Production production, ProductionDto productionDto) {
        if ( productionDto == null ) {
            return production;
        }

        if ( productionDto.getId() != null ) {
            production.setId( productionDto.getId() );
        }
        if ( productionDto.getProductionNumber() != null ) {
            production.setProductionNumber( productionDto.getProductionNumber() );
        }
        if ( productionDto.getName() != null ) {
            production.setName( productionDto.getName() );
        }
        if ( productionDto.getLastBatch() != null ) {
            production.setLastBatch( productionDto.getLastBatch() );
        }
        production.setFinalized( productionDto.isFinalized() );
        if ( productionDto.getStartDate() != null ) {
            production.setStartDate( productionDto.getStartDate() );
        }
        if ( productionDto.getEndDate() != null ) {
            production.setEndDate( productionDto.getEndDate() );
        }
        if ( productionDto.getStaff() != null ) {
            if ( production.getStaff() == null ) {
                production.setStaff( new Staff() );
            }
            staffMapper.partialUpdate( productionDto.getStaff(), production.getStaff() );
        }
        if ( productionDto.getProductionStore() != null ) {
            if ( production.getProductionStore() == null ) {
                production.setProductionStore( new ProductionStore() );
            }
            productionStoreMapper.partialUpdate( productionDto.getProductionStore(), production.getProductionStore() );
        }

        linkProductionStore( production );

        return production;
    }

    @Override
    public Production toEntity(ProductionDetailsDto1 productionDetailsDto1) {
        if ( productionDetailsDto1 == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( productionDetailsDto1.getId() );
        production.setProductionNumber( productionDetailsDto1.getProductionNumber() );
        production.setName( productionDetailsDto1.getName() );
        production.setLastBatch( productionDetailsDto1.getLastBatch() );
        production.setFinalized( productionDetailsDto1.isFinalized() );
        production.setStartDate( productionDetailsDto1.getStartDate() );
        production.setEndDate( productionDetailsDto1.getEndDate() );
        production.setStaff( staffMapper.toEntity( productionDetailsDto1.getStaff() ) );
        production.setProductionStore( productionStoreMapper.toEntity( productionDetailsDto1.getProductionStore() ) );

        linkProductionStore( production );

        return production;
    }

    @Override
    public ProductionDetailsDto1 toDto1(Production production) {
        if ( production == null ) {
            return null;
        }

        Long id = null;
        String productionNumber = null;
        String name = null;
        Integer lastBatch = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        StaffDto staff = null;
        boolean finalized = false;
        ProductionStoreDto productionStore = null;

        id = production.getId();
        productionNumber = production.getProductionNumber();
        name = production.getName();
        lastBatch = production.getLastBatch();
        startDate = production.getStartDate();
        endDate = production.getEndDate();
        staff = staffMapper.toDto( production.getStaff() );
        finalized = production.isFinalized();
        productionStore = productionStoreMapper.toDto( production.getProductionStore() );

        ProductionDetailsDto1 productionDetailsDto1 = new ProductionDetailsDto1( id, productionNumber, name, lastBatch, startDate, endDate, staff, finalized, productionStore );

        return productionDetailsDto1;
    }

    @Override
    public Production partialUpdate(ProductionDetailsDto1 productionDetailsDto1, Production production) {
        if ( productionDetailsDto1 == null ) {
            return production;
        }

        if ( productionDetailsDto1.getId() != null ) {
            production.setId( productionDetailsDto1.getId() );
        }
        if ( productionDetailsDto1.getProductionNumber() != null ) {
            production.setProductionNumber( productionDetailsDto1.getProductionNumber() );
        }
        if ( productionDetailsDto1.getName() != null ) {
            production.setName( productionDetailsDto1.getName() );
        }
        if ( productionDetailsDto1.getLastBatch() != null ) {
            production.setLastBatch( productionDetailsDto1.getLastBatch() );
        }
        production.setFinalized( productionDetailsDto1.isFinalized() );
        if ( productionDetailsDto1.getStartDate() != null ) {
            production.setStartDate( productionDetailsDto1.getStartDate() );
        }
        if ( productionDetailsDto1.getEndDate() != null ) {
            production.setEndDate( productionDetailsDto1.getEndDate() );
        }
        if ( productionDetailsDto1.getStaff() != null ) {
            if ( production.getStaff() == null ) {
                production.setStaff( new Staff() );
            }
            staffMapper.partialUpdate( productionDetailsDto1.getStaff(), production.getStaff() );
        }
        if ( productionDetailsDto1.getProductionStore() != null ) {
            if ( production.getProductionStore() == null ) {
                production.setProductionStore( new ProductionStore() );
            }
            productionStoreMapper.partialUpdate( productionDetailsDto1.getProductionStore(), production.getProductionStore() );
        }

        linkProductionStore( production );

        return production;
    }

    @Override
    public Production toEntity(ProdDashboardData prodDashboardData) {
        if ( prodDashboardData == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( prodDashboardData.getId() );
        production.setProductionNumber( prodDashboardData.getProductionNumber() );
        production.setName( prodDashboardData.getName() );
        production.setFinalized( prodDashboardData.isFinalized() );
        production.setStartDate( prodDashboardData.getStartDate() );
        production.setEndDate( prodDashboardData.getEndDate() );
        production.setStaff( staffMapper.toEntity( prodDashboardData.getStaff() ) );

        linkProductionStore( production );

        return production;
    }

    @Override
    public ProdDashboardData toDto2(Production production) {
        if ( production == null ) {
            return null;
        }

        Long id = null;
        String productionNumber = null;
        String name = null;
        boolean finalized = false;
        LocalDate startDate = null;
        LocalDate endDate = null;
        StaffDto staff = null;

        id = production.getId();
        productionNumber = production.getProductionNumber();
        name = production.getName();
        finalized = production.isFinalized();
        startDate = production.getStartDate();
        endDate = production.getEndDate();
        staff = staffMapper.toDto( production.getStaff() );

        ProdDashboardData prodDashboardData = new ProdDashboardData( id, productionNumber, name, finalized, startDate, endDate, staff );

        return prodDashboardData;
    }

    @Override
    public Production partialUpdate(ProdDashboardData prodDashboardData, Production production) {
        if ( prodDashboardData == null ) {
            return production;
        }

        if ( prodDashboardData.getId() != null ) {
            production.setId( prodDashboardData.getId() );
        }
        if ( prodDashboardData.getProductionNumber() != null ) {
            production.setProductionNumber( prodDashboardData.getProductionNumber() );
        }
        if ( prodDashboardData.getName() != null ) {
            production.setName( prodDashboardData.getName() );
        }
        production.setFinalized( prodDashboardData.isFinalized() );
        if ( prodDashboardData.getStartDate() != null ) {
            production.setStartDate( prodDashboardData.getStartDate() );
        }
        if ( prodDashboardData.getEndDate() != null ) {
            production.setEndDate( prodDashboardData.getEndDate() );
        }
        if ( prodDashboardData.getStaff() != null ) {
            if ( production.getStaff() == null ) {
                production.setStaff( new Staff() );
            }
            staffMapper.partialUpdate( prodDashboardData.getStaff(), production.getStaff() );
        }

        linkProductionStore( production );

        return production;
    }
}
