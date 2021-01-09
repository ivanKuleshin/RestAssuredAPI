package com.erat.RestAssuredAPI.APIs;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Random;

import static com.erat.RestAssuredAPI.pojoClasses.CustomerAddressPojo.*;
import static com.erat.RestAssuredAPI.pojoClasses.CustomerPojo.*;
import static io.restassured.RestAssured.given;

/**
 * This class is using to perform requests to create a Customer
 */
public class CreateCustomerAPI extends BaseTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Response sendPostRequestToCreateCustomer(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                formParams(testDataMap).
                log().uri().log().parameters().request(RequestTypes.POST.getValue(), properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        return response;
    }

    public Response sendPostRequestToCreateCustomerUsingDefaultPojo() {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                formParams(objectMapper.convertValue(getDefaultCustomerPojo(), new TypeReference<Map<String, String>>() {
                })).
                formParams(getCustomerAddressAsTestMap(getDefaultCustomerAddressPojo())).
                log().all().request(RequestTypes.POST.getValue(), properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        return response;
    }

    public Response sendPostRequestToCreateCustomerUsingPojo(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                formParams(objectMapper.convertValue(getCustomerPojoFromMap(testDataMap), new TypeReference<Map<String, String>>() {
                })).
                formParams(getCustomerAddressAsTestMap(getCustomerAddressPojoFromMap(testDataMap))).
                log().all().request(RequestTypes.POST.getValue(), properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        return response;
    }

    public Response sendPostRequestToCreateCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey) + new Random().nextInt(100)).
                formParams(testDataMap).
                log().uri().log().parameters().request(RequestTypes.POST.getValue(), properties.getProperty(customerAPIEndPoint));

        response.prettyPrint();
        return response;
    }
}