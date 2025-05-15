package com.justme8code.utterfresh_production_gathering_sys.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Map<String, Object> map) throws Exception {
        return objectMapper.writeValueAsString(map);
    }

    public static Map<String, Object> fromJson(String json) throws Exception {
        return objectMapper.readValue(json, new TypeReference<>() {
        });
    }
}
