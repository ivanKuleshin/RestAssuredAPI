package com.erat.RestAssuredAPI.APIs.paypal;

import com.erat.RestAssuredAPI.pojoClasses.paypal.OrderPojo;
import com.erat.RestAssuredAPI.pojoClasses.paypal.PurchaseUnitsPojo;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.PayPalBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.*;

@Slf4j
public class PayPalAPI extends PayPalBaseTest {
    public String getAuthKey() {
        basePath = BASE_PATH1;

        Response response = given().param("grant_type", "client_credentials").auth().preemptive().basic(payPalUserName, payPalPassword).post(getPayPalTokenURI);

        log.info("Requested URI: {}", baseURI + basePath + getPayPalTokenURI);
        log.info("Response is: \n{}", response.asPrettyString());

        if (response.statusCode() == BaseTest.StatusCodes.OK.getValue()) {
            return response.jsonPath().getString("access_token");
        } else {
            throw new RuntimeException("Access token was not received");
        }
    }


    public Response createOrder(Map<String, String> testDataMap) {
        basePath = BASE_PATH2;

        OrderPojo orderBody = new OrderPojo(testDataMap.get("intent"), Collections.singletonList(new PurchaseUnitsPojo(testDataMap.get("currency_code"), testDataMap.get("value"))));

        Response response = given().auth().oauth2(accessToken).contentType(ContentType.JSON).body(orderBody).post(payPalOrderURI);

        log.info("{} Request URI was send: {}", RequestTypes.POST.getValue(), baseURI + basePath + payPalOrderURI);
        log.info("Response is: \n{}", response.asPrettyString());
        return response;
    }

    public Response getOrder(final String createdOrderId){
        basePath = BASE_PATH2;

        Response response = given().auth().oauth2(accessToken).get(payPalOrderURI + "/" + createdOrderId);

        log.info("{} Request URI was send: {}", RequestTypes.POST.getValue(), payPalOrderURI + "/" + createdOrderId);
        log.info("Response is: \n{}", response.asPrettyString());
        return response;
    }
}
