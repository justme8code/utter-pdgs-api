package com.justme8code.utterfresh_production_gathering_sys.models;

import com.justme8code.utterfresh_production_gathering_sys.utils.JsonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class DynamicData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Lob  // H2 supports LOB for large JSON storage
    @Column(columnDefinition = "CLOB")
    private String jsonData;

    @Transient // Don't store in DB, used for JSON parsing
    private Map<String, Object> dataMap;

    public void setDataMap(Map<String, Object> map) throws Exception {
        this.dataMap = map;
        this.jsonData = JsonUtils.toJson(map);
    }

    public Map<String, Object> getDataMap() throws Exception {
        if (this.dataMap == null && this.jsonData != null) {
            this.dataMap = JsonUtils.fromJson(jsonData);
        }
        return this.dataMap;
    }
}
