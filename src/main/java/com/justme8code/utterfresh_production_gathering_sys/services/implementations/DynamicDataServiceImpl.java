package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.DynamicData;
import com.justme8code.utterfresh_production_gathering_sys.repository.DynamicDataRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.DynamicDataService;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DynamicDataServiceImpl implements DynamicDataService {

    private final DynamicDataRepository repository;

    @Autowired
    public DynamicDataServiceImpl(DynamicDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> findById(Long id) throws Exception {
        Optional<DynamicData> optionalData = repository.findById(id);
        if(optionalData.isPresent()) {
            return optionalData.get().getDataMap();
        }else {
            return null;
        }
    }

    @Override
    public Map<String, Object> findByName(String name) throws Exception {
        Optional<DynamicData> optionalData = repository.findByName(name);
        if(optionalData.isPresent()) {
            return optionalData.get().getDataMap();
        }else {
            return null;
        }
    }

    @Override
    public Long createData(String name, Map<String, Object> data) throws JsonProcessingException {
        try{
            DynamicData dynamicData = new DynamicData();
            dynamicData.setName(name);
            dynamicData.setDataMap(data);
            return repository.save(dynamicData).getId();
        }catch (Exception e){
            throw new RuntimeException("Error creating data", e);
        }

    }

    @Override
    public void updateData(Long id, Map<String, Object> data) throws JsonProcessingException {
         try{
             Optional<DynamicData> optionalData = repository.findById(id);
             if (optionalData.isPresent()) {
                 DynamicData existingData = optionalData.get();
                 existingData.setDataMap(data);
                 repository.save(existingData);
             } else {
                 throw new RuntimeException("Data not found!");
             }
         }catch (Exception e){
             throw new RuntimeException("Error creating data", e);
         }
    }

    @Override
    public void deleteData(Long id) {
        repository.deleteById(id);
    }
}
