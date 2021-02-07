package com.erat.RestAssuredAPI.APIs.stripe;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;

import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo.*;
import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerPojo.*;
import static io.restassured.RestAssured.*;

/**
 * This class is using to perform requests to create a Customer
 */
@Slf4j
public class CreateCustomerAPI extends StripeBaseTest {
    public Response sendPostRequestToCreateCustomer(Map<String, Object> testDataMap) {
        Response response = given().auth().oauth2(validSecretKey).
                formParams(testDataMap).
                request(RequestTypes.POST.getValue(), customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }

    public Response sendPostRequestToCreateCustomerUsingDefaultPojo() {
        Map<String, String> testDataMap = getCustomerAddressAsTestMap(getDefaultCustomerAddressPojo());

        Response response = given().auth().oauth2(validSecretKey).
                formParams(objectMapper.convertValue(getDefaultCustomerPojo(), new TypeReference<Map<String, String>>() {
                })).
                formParams(testDataMap).request(RequestTypes.POST.getValue(), customerAPIEndPoint);

        log.info("Sent {} request to the: {}", RequestTypes.POST.getValue(), baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString().trim());
        return response;
    }

    public Response sendPostRequestToCreateCustomerUsingPojo(Map<String, Object> testDataMap) {
        Response response = given().auth().oauth2(validSecretKey).
                formParams(objectMapper.convertValue(getCustomerPojoFromMap(testDataMap), new TypeReference<Map<String, String>>() {
                })).
                formParams(getCustomerAddressAsTestMap(getCustomerAddressPojoFromMap(testDataMap)))
                .request(RequestTypes.POST.getValue(), customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }

    public Response sendPostRequestToCreateCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = given().auth().oauth2(validSecretKey + new Random().nextInt(100)).
                formParams(testDataMap).
                request(RequestTypes.POST.getValue(), customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }
}