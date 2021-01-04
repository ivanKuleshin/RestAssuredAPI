package com.erat.RestAssuredAPI.APIs;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {
    public Response sendPostRequestToCreateCustomer(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                formParams(testDataMap).
                log().uri().log().parameters().post(properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        return response;
    }

    public Response sendPostRequestToCreateCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey) + new Random().nextInt(100)).
                formParams(testDataMap).
                log().uri().log().parameters().post(properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        return response;
    }
}
