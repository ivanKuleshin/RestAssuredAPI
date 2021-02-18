package com.erat.RestAssuredAPI.testCases.stripe;

import com.erat.RestAssuredAPI.APIs.stripe.CreateCustomerAPI;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo.getCustomerAddressAsTestMap;
import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo.getDefaultCustomerAddressPojo;
import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerPojo.getCustomerPojoAsMap;
import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerPojo.getDefaultCustomerPojo;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class CreateCustomerTest extends CreateCustomerAPI {
    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithValidTokenUsingPojo(Map<String, String> testDataMap) {
        Response response = sendPostRequestToCreateCustomerUsingPojo(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
        testUtil.validateCustomerResponse(testDataMap, response);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = sendPostRequestToCreateCustomerWithInvalidToken(testDataMap).
                then().statusCode(StatusCodes.UNAUTHORIZED.getValue()).and().extract().response();

        String actualType = response.jsonPath().getMap("error").get("type").toString();
        assertThat(actualType).isEqualTo(INVALID_EXPECTED_TYPE);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithValidToken2(Map<String, String> testDataMap) {
        Response response = sendPostRequestToCreateCustomer(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
    }

    @Test()
    public void createCustomerWithDefaultValues() {
        Map<String, String> testDataMap = getCustomerAddressAsTestMap(getDefaultCustomerAddressPojo());
        testDataMap.putAll(getCustomerPojoAsMap(getDefaultCustomerPojo()));
        testDataMap.remove("id");

        Response response = sendPostRequestToCreateCustomer(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
        testUtil.validateCustomerResponse(testDataMap, response);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithInvalidToken2(Map<String, String> testDataMap) {
        createCustomerWithInvalidToken(testDataMap);
    }
}