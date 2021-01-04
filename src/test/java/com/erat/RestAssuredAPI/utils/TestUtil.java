package com.erat.RestAssuredAPI.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

public class TestUtil {
    public void actualMapContainsExpected(Map<String, String> actualMap, Map<String, String> expectedMap){
        assertThat(actualMap.entrySet().containsAll(expectedMap.entrySet())).isTrue();
    }
}
