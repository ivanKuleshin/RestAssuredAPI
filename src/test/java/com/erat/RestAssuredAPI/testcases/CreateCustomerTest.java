package com.erat.RestAssuredAPI.testcases;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class CreateCustomerTest extends BaseTest {
    private static final String EXPECTED_TYPE = "invalid_request_error";

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithValidToken(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                formParams(testDataMap).
                log().uri().post(properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        String customerId = response.getBody().path("id").toString();
        System.out.println("The ID of the new customer is: " + customerId);
        System.out.println("The customer's address is: " + response.jsonPath().get("address.line1"));
        System.out.println("The first customer's preferred locale is: " + response.jsonPath().get("preferred_locales[0]"));
        System.out.println("The size of address's map is: " + response.jsonPath().getMap("address").size());
        System.out.println("The size of main map is: " + response.jsonPath().getMap("$").size());
        System.out.println("The customer's address is: " + response.jsonPath().getMap("address").get("line1"));
        System.out.println("The size of preferred_locales list is: " + response.jsonPath().getList("preferred_locales").size());

        System.setProperty("lastCreatedCustomer", customerId);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey) + new Random().nextInt(100)).
                formParams(testDataMap).
                log().uri().post(properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.UNAUTHORIZED.getValue());
        String actualType = response.jsonPath().getMap("error").get("type").toString();
        assertThat(actualType).isEqualTo(EXPECTED_TYPE);
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