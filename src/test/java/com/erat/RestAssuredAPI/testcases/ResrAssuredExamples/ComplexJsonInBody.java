package com.erat.RestAssuredAPI.testcases.ResrAssuredExamples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.Map;

public class ComplexJsonInBody {
    public static void main(String[] args) {
        Map<String, Object> map = Map.of(
                "email", "complexJson@testmail.com",
                "firstName", "Complex",
                "lastName", "Json",
                "phone", Arrays.asList("13337", "1488"),
                "address", Map.of("flatNo", "131-A", "city", "Kyiv", "state", "Kyiv", "country", "Ukraine"));

        Response response = given().contentType(ContentType.JSON).log().all().body(map).post("http://localhost:8080/api/users");

        response.prettyPrint();
        System.out.println(response.getStatusCode());
    }
}
