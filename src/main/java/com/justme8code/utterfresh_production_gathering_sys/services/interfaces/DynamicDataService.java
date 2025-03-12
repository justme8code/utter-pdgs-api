package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import java.util.Map;

public interface DynamicDataService {

    Map<String, Object> findById(Long id) throws Exception;

    Map<String, Object> findByName(String name) throws Exception;

    Long createData(String name, Map<String, Object> data) throws Exception;

    void updateData(Long id, Map<String, Object> data) throws Exception;

    void deleteData(Long id);
}
