package com.testcrew.stc.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public static List<Map<String, Object>> readJsonFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> data = null;
        try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream("testdata.json")) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }
            data = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
