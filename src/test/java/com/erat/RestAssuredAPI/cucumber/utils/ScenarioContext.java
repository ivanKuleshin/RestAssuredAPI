package com.erat.RestAssuredAPI.cucumber.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(HashMap::new);

    public void setContext(String key, Object value) {
        context.get().put(key, value);
    }

    public <T> T getContext(String key) {
        ObjectMapper mapper = new ObjectMapper();
        Object value = context.get().get(key);
        if (value != null) {
            return mapper.convertValue(value, new TypeReference<T>() {});
        }
        return null;
    }

    @Nullable
    public <T> T getContext(String key, Class<T> type) {
        Object value = context.get().get(key);
        if (value != null) {
            return type.cast(value);
        }
        return null;
    }

    public <T> T getContext(String key, TypeReference<T> typeRef){
        ObjectMapper mapper = new ObjectMapper();
        Object value = context.get().get(key);
        if (value != null) {
            return mapper.convertValue(value, typeRef);
        }
        return null;
    }

    public Boolean isContains(String key) {
        return context.get().containsKey(key);
    }
}