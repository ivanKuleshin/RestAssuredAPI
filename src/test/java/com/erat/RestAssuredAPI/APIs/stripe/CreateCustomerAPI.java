package com.erat.RestAssuredAPI.APIs.stripe;

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
    public Response sendPostRequestToCreateCustomer(Map<String, String> testDataMap) {
        setRequestSpecification();
        Response response = restClient.sendRequestWithSpecifications(requestSpecification.formParams(testDataMap),
                RequestTypes.POST, customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }

    public Response sendPostRequestToCreateCustomerUsingPojo(Map<String, String> testDataMap) {
        setRequestSpecification();
        Response response = restClient.sendRequestWithSpecifications(
                requestSpecification.formParams(objectMapper.convertValue(getCustomerPojoFromMap(testDataMap),
                        new TypeReference<Map<String, String>>() {
                        })).
                        formParams(getCustomerAddressAsTestMap(getCustomerAddressPojoFromMap(testDataMap))), RequestTypes.POST, customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }

    public Response sendPostRequestToCreateCustomerWithInvalidToken(Map<String, String> testDataMap) {
        setRequestSpecification();
        Response response = restClient.sendRequestWithSpecifications(given().auth().
                oauth2(validSecretKey + new Random().nextInt(100)).
                formParams(testDataMap), RequestTypes.POST, customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }
}