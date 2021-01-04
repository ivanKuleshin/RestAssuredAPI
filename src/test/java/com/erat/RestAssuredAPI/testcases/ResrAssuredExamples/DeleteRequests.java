package com.erat.RestAssuredAPI.testcases.ResrAssuredExamples;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteRequests {
    private static final String token = "sk_test_51I3N4DCjw1mgrNWQp3LxVem3R4SZhIqISesbED9Wlt24ujX1CaEPBHD9tUGtRC5hb3ZrZq7JS8dHbOn5wBGn9yIj00FxWcPpQG";
    private static final String BASE_URI = "https://api.stripe.com/v1/customers";

    public static void deleteCustomer(String customerId) {
        Response response = given().header("Authorization", "Bearer " + token).log().uri().delete(BASE_URI + "/" + customerId);

        response.prettyPrint();
    }
}
