package com.example.fileupload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapperTest {

    @Test
    void objectMapperTest() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> errorResult = new HashMap<>();
        RuntimeException exception = new RuntimeException("Runtime Exception");
        errorResult.put("ex", exception);
        errorResult.put("message", exception.getMessage());

        String s = objectMapper.writeValueAsString(errorResult);
        System.out.println(s);

    }
}
