package com.erat.RestAssuredAPI.testcases;

import com.erat.RestAssuredAPI.APIs.CreateCustomerAPI;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class CreateCustomerTest extends BaseTest {
    private static final CreateCustomerAPI createCustomerAPI =  new CreateCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithValidToken(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
        assertThat(response.jsonPath().getString("address.city")).isEqualTo(testDataMap.get("address[city]"));
        assertThat(response.jsonPath().getString("description")).isEqualTo(testDataMap.get("description"));
        assertThat(response.jsonPath().getString("email")).isEqualTo(testDataMap.get("email"));
        assertThat(response.jsonPath().getString("name")).isEqualTo(testDataMap.get("name"));
        assertThat(response.jsonPath().getString("phone")).isEqualTo(testDataMap.get("phone"));
        assertThat(response.jsonPath().getString("preferred_locales[0]")).isEqualTo(testDataMap.get("preferred_locales[0]"));
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomerWithInvalidToken(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.UNAUTHORIZED.getValue());
        String actualType = response.jsonPath().getMap("error").get("type").toString();
        assertThat(actualType).isEqualTo(INVALID_EXPECTED_TYPE);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithValidToken2(Map<String, String> testDataMap) {
        createCustomerWithValidToken(testDataMap);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithInvalidToken2(Map<String, String> testDataMap) {
        createCustomerWithInvalidToken(testDataMap);
    }
}