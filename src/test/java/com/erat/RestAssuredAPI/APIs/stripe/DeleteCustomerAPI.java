package com.erat.RestAssuredAPI.APIs.stripe;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.*;

/**
 * This class is using to perform requests to delete a Customer
 */
@Slf4j
public class DeleteCustomerAPI extends StripeBaseTest {
    public Response sendDeleteRequestToDeleteCustomer(String customerId) {
        Response response = given().auth().oauth2(validSecretKey).
                request(RequestTypes.DELETE.getValue(), customerAPIEndPoint + "/" + customerId);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", customerId);
        log.info("Response is: \n{}", response.asString());
        return response;
    }
}
