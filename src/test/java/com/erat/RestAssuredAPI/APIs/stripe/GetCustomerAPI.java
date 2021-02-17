package com.erat.RestAssuredAPI.APIs.stripe;

import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.*;

@Slf4j
public class GetCustomerAPI extends StripeBaseTest {
    public Response sendGetRequestToRetrieveCustomer(String customerId) {
        setRequestSpecification();

        Response response = restClient.sendRequestWithSpecifications(requestSpecification, RequestTypes.GET, customerAPIEndPoint + "/" + customerId);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", customerId);
        log.info("Response is: \n{}", response.asString());
        return response;
    }

    public Response sendGetRequestToRetrieveAllCustomers() {
        setRequestSpecification();

        Response response = restClient.sendRequestWithSpecifications(requestSpecification, RequestTypes.GET, customerAPIEndPoint);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", "");
        log.info("Response is: \n{}", response.asString());
        return response;
    }
}