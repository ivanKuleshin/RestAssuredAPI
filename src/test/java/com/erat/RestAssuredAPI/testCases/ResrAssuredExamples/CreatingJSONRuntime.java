package com.erat.RestAssuredAPI.testCases.ResrAssuredExamples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreatingJSONRuntime {
    public static void main(String[] args){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "jsonObject@testmail.com");
        jsonObject.put("firstName", "Json");
        jsonObject.put("lastName", "Object");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(1337);
        jsonArray.put(1488);

        jsonObject.put("mobile", jsonArray);

        Response response = given().contentType(ContentType.JSON).log().all().body(jsonObject.toString()).post("http://localhost:8080/api/users");

        response.prettyPrint();
        System.out.println(response.getStatusCode());
    }
}
