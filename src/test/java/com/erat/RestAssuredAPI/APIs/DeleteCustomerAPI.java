package com.erat.RestAssuredAPI.APIs;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.*;

/**
 * This class is using to perform requests to delete a Customer
 */
@Slf4j
public class DeleteCustomerAPI extends BaseTest {
    public Response sendDeleteRequestToDeleteCustomer(String customerId) {
        Response response = given().auth().oauth2(validSecretKey).
                request(RequestTypes.DELETE.getValue(), customerAPIEndPoint + "/" + customerId);

        log.info("Requested URI: {}", baseURI + basePath + customerAPIEndPoint);
        log.info("Parameters: {}", customerId);
        log.info("Response is: \n{}", response.asString());
        return response;
    }
}
