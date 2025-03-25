package com.justme8code.utterfresh_production_gathering_sys.models;

import com.justme8code.utterfresh_production_gathering_sys.utils.JsonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@Getter
@Setter
public class DynamicData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", columnDefinition="TEXT")
    private String jsonData;

    @Transient
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
