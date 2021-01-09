package com.erat.RestAssuredAPI.APIs;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetCustomerAPI extends BaseTest {
    public Response sendGetRequestToRetrieveCustomer(String customerId) {
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                log().uri().
                request(RequestTypes.GET.getValue(), properties.getProperty(customerAPIEndPoint) + "/" + customerId);
        response.prettyPrint();
        return response;
    }

    public Response sendGetRequestToRetrieveAllCustomers() {
        return given().auth().oauth2(properties.getProperty(validSecretKey)).
                log().uri().
                request(RequestTypes.GET.getValue(), properties.getProperty(customerAPIEndPoint));
    }
}