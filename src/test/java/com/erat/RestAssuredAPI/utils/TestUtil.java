package com.erat.RestAssuredAPI.utils;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.*;

import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo.getCustomerAddressAsNormalMap;

import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    public void actualMapContainsExpected(Map<String, Object> actualMap, Map<String, Object> expectedMap){
        SoftAssertions softAssertions = new SoftAssertions();
        for (Map.Entry<String, Object> entry : expectedMap.entrySet()){
            if(actualMap.containsKey(entry.getKey())){
                softAssertions.assertThat(actualMap.get(entry.getKey())).isEqualTo(entry.getValue());
            } else
                throw new RuntimeException("Actual map does NOT contain key from expected map. The key is: " + entry.getKey());
        }
        softAssertions.assertAll();
    }

    public void actualMapIsEqualToExpected(Map<String, Object> actualMap, Map<String, Object> expectedMap) {
        assertThat(actualMap.size()).as("Actual and expected maps have teh different size!").isEqualTo(expectedMap.size());

        SoftAssertions softAssertions = new SoftAssertions();
        for (Map.Entry<String, Object> entry : actualMap.entrySet()) {
            String key = entry.getKey();
            String actualValue = entry.getValue().toString();
            String expectedValue = expectedMap.get(key).toString();
            softAssertions.assertThat(actualValue).as("Key: %s Actual value: %s Expected value: %s", key, actualValue, expectedValue).
                    isEqualTo(expectedValue);
        }
        softAssertions.assertAll();
    }

    public void validateCustomerResponse(Map<String, String> actualTestDataMap, Response response){
        Map<String, Object> expectedTestData = getExpectedData(actualTestDataMap);
        expectedTestData.put("address", getCustomerAddressAsNormalMap(actualTestDataMap));
        actualMapContainsExpected(response.jsonPath().getMap("$"), expectedTestData);
    }

    /**
     * This method is using to build an expected data Map due to the unusual response structure.
     * @param actualTestDataMap - input test data Map
     * @return Method returns expected data Map
     */
    public Map<String, Object> getExpectedData(Map<String, String> actualTestDataMap){
        Map<String, Object> expectedTestData = new HashMap<>();

        for(Map.Entry<String, String> entry : actualTestDataMap.entrySet()){
            if (!entry.getKey().contains("[")){
                expectedTestData.put(entry.getKey(), entry.getValue());
            }
        }
        return expectedTestData;
    }
}