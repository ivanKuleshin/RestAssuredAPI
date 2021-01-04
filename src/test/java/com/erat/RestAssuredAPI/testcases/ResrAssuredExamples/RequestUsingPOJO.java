package com.erat.RestAssuredAPI.testcases.ResrAssuredExamples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class RequestUsingPOJO {
    public static void main(String[] args) {
        UserPOJO userPOJO = new UserPOJO("POJO@testmail.com", "Test", "POJO", Arrays.asList(1337, 1488),
                new AddressPOJO("131-A", "kyiv", "Kyiv", "Ukraine"));

        Response response = given().contentType(ContentType.JSON).log().all().body(userPOJO).post("http://localhost:8080/api/users");

        response.prettyPrint();
        System.out.println(response.getStatusCode());
    }
}
