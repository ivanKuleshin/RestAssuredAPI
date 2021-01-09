package com.erat.RestAssuredAPI.APIs;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

public class UpdateCustomerAPI extends BaseTest {
    public Response sendPostRequestToUpdateCustomer(Map<String, Object> testDataMap, String customerId){
        Response response = given().auth().oauth2(properties.getProperty(validSecretKey)).
                log().parameters().
                formParams(testDataMap).
                request(RequestTypes.POST.getValue(), properties.getProperty(customerAPIEndPoint) + "/" + customerId);
        response.prettyPrint();
        return response;
    }
}
