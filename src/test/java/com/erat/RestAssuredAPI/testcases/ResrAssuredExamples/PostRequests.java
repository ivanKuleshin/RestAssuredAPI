package com.erat.RestAssuredAPI.testcases.ResrAssuredExamples;

import io.restassured.response.Response;

import java.util.Map;

import static com.erat.RestAssuredAPI.testcases.ResrAssuredExamples.DeleteRequests.deleteCustomer;
import static io.restassured.RestAssured.*;

public class PostRequests {
    private static final String token = "sk_test_51I3N4DCjw1mgrNWQp3LxVem3R4SZhIqISesbED9Wlt24ujX1CaEPBHD9tUGtRC5hb3ZrZq7JS8dHbOn5wBGn9yIj00FxWcPpQG";
    private static final String BASE_URI = "https://api.stripe.com/v1/customers";

    public static void main(String[] args) {
        createCustomer();

        deleteCustomer(System.getProperty("lastCreatedCustomer"));
//        deleteCustomer("cus_IepIz4JDssmJpf");
    }

    private static void createCustomer() {
        Response response = given().auth().oauth2(token).formParams(Map.of(
                "email", "restAssured@testmail.com",
                "name", "Rest Assured",
                "description", "This is a Rest Assured customer",
                "phone", "38014881337",
                "address[line1]", "Kyiv",
                "preferred_locales[0]", "english")).
                log().uri().post(BASE_URI);

        response.prettyPrint();

        String customerId = response.getBody().path("id").toString();
        System.out.println("The ID of the new customer is: " + customerId);
        System.out.println("The customer's address is: " + response.jsonPath().get("address.line1"));
        System.out.println("The first customer's preferred locale is: " + response.jsonPath().get("preferred_locales[0]"));
        System.out.println("The size of address's map is: " + response.jsonPath().getMap("address").size());
        System.out.println("The size of main map is: " + response.jsonPath().getMap("$").size());
        System.out.println("The customer's address is: " + response.jsonPath().getMap("address").get("line1"));
        System.out.println("The size of preferred_locales list is: " + response.jsonPath().getList("preferred_locales").size());

        System.setProperty("lastCreatedCustomer", customerId);
    }
}