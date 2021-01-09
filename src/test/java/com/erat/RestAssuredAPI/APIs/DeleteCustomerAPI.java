package com.erat.RestAssuredAPI.APIs;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * This class is using to perform requests to delete a Customer
 */
public class DeleteCustomerAPI extends BaseTest {
    public Response sendDeleteRequestToDeleteCustomer(String customerId) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                log().uri().log().parameters().
                request(RequestTypes.DELETE.getValue(), properties.getProperty(customerAPIEndPoint) + "/" + customerId);

        response.prettyPrint();
        return response;
    }
}
