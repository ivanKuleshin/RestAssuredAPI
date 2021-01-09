package com.erat.RestAssuredAPI.testCases.ResrAssuredExamples;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetRequests {
    private static final String token = "sk_test_51I3N4DCjw1mgrNWQp3LxVem3R4SZhIqISesbED9Wlt24ujX1CaEPBHD9tUGtRC5hb3ZrZq7JS8dHbOn5wBGn9yIj00FxWcPpQG";
    private static final String BASE_URI = "https://api.stripe.com/v1/customers";
    private static final String FIRST_CUSTOMER_ID = "cus_IegqfwOhJFtfeN";

    public static void main(String[] args) {
        getAllCustomers();

         getCustomerByID();
    }

    private static void getAllCustomers() {
//        given().header("content-type", "application/json");
//        given().contentType(ContentType.JSON);
        Response response = given().param("limit", 1).auth().oauth2(token).log().uri().get(BASE_URI);

        System.out.println("All customers:");
        System.out.print(response.asString());

        System.out.println("Response code is: " + response.statusCode() + "\n");
    }

    private static void getCustomerByID() {
        Response response = given().header("Authorization", "Bearer " + token).log().uri().get(BASE_URI + "/" + FIRST_CUSTOMER_ID);

        System.out.println("Customer with ID " + FIRST_CUSTOMER_ID + ":");
        System.out.println(response.asString());
    }
}
