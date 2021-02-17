package com.erat.RestAssuredAPI.APIs.stripe;

import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static io.restassured.RestAssured.*;

@Slf4j
public class UpdateCustomerAPI extends StripeBaseTest {
    public Response sendPostRequestToUpdateCustomer(Map<String, String> testDataMap, String customerId){
        setRequestSpecification();

        Response response = restClient.sendRequestWithSpecifications(requestSpecification.formParams(testDataMap),
                RequestTypes.POST, customerAPIEndPoint + "/" + customerId);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint + "/" + customerId);
        log.info("Parameters: {}", testDataMap);
        log.info("Response is: \n{}", response.asString());
        return response;
    }
}
