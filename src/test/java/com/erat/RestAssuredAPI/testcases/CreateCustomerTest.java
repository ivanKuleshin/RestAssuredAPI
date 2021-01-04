package com.erat.RestAssuredAPI.testcases;

import com.erat.RestAssuredAPI.APIs.CreateCustomerAPI;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class CreateCustomerTest extends BaseTest {
    private static final CreateCustomerAPI createCustomerAPI =  new CreateCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithValidToken(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);

        SoftAssertions softAssertions  = new SoftAssertions();
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
        softAssertions.assertThat(response.jsonPath().getString("address.city")).isEqualTo(testDataMap.get("address[city]"));
        softAssertions.assertThat(response.jsonPath().getString("address.country")).isNull();
        softAssertions.assertThat(response.jsonPath().getString("balance")).isEqualTo("0");
        softAssertions.assertThat(response.jsonPath().getString("currency")).isNull();
        softAssertions.assertThat(response.jsonPath().getString("delinquent")).isEqualTo(String.valueOf(false));
        softAssertions.assertThat(response.jsonPath().getString("discount")).isNull();
        softAssertions.assertThat(response.jsonPath().getString("livemode")).isEqualTo(String.valueOf(false));
        softAssertions.assertThat(response.jsonPath().getString("preferred_locales[0]")).isEqualTo(testDataMap.get("preferred_locales[0]"));
        softAssertions.assertThat(response.jsonPath().getString("shipping")).isNull();

        softAssertions.assertAll();

        testDataMap.remove("address[city]");
        testDataMap.remove("preferred_locales[0]");
        testUtil.actualMapContainsExpected(response.jsonPath().getMap("$"), testDataMap);
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