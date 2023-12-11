package com.training.multithreadiing.task5_ExchangeCurrencies.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static String toJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }
}

