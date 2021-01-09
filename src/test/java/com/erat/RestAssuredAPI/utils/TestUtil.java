package com.erat.RestAssuredAPI.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    public void actualMapContainsExpected(Map<String, String> actualMap, Map<String, Object> expectedMap){
        assertThat(actualMap.entrySet().containsAll(expectedMap.entrySet())).isTrue();
    }

    /**
     * This method is using to build an expected data Map due to the unusual response structure.
     * @param testDataMap - input test data Map
     * @return Method returns expected data Map
     */
    public Map<String, Object> getExpectedData(Map<String, String> testDataMap){
        Map<String, Object> expectedTestData = new HashMap<>();

        for(Map.Entry<String, String> entry : testDataMap.entrySet()){
            if (!entry.getKey().contains("[")){
                expectedTestData.put(entry.getKey(), entry.getValue());
            }
        }
        return expectedTestData;
    }
}
