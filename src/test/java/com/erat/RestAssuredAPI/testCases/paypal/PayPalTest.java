package com.erat.RestAssuredAPI.testCases.paypal;

import com.erat.RestAssuredAPI.pojoClasses.paypal.OrderPojo;
import com.erat.RestAssuredAPI.pojoClasses.paypal.PurchaseUnitsPojo;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.PayPalBaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class PayPalTest extends PayPalBaseTest {
    private static String accessToken;
    private static String createdOrderId;

    private final static String CREATED_STATUS = "CREATED";
    private final static String BASE_PATH1 = "/v1";
    private final static String BASE_PATH2 = "/v2";

    @Test
    public void getAuthToken() {
        basePath = BASE_PATH1;

        Response response = given().param("grant_type", "client_credentials").auth().preemptive().basic(payPalUserName, payPalPassword).post(getPayPalTokenURI);

        log.info("Requested URI: {}", baseURI + basePath + getPayPalTokenURI);
        log.info("Response is: \n{}", response.asPrettyString());

        if (response.statusCode() == BaseTest.StatusCodes.OK.getValue()) {
            accessToken = response.jsonPath().getString("access_token");
        } else {
            throw new RuntimeException("Access token was not received");
        }
    }

    @Test(priority = 1, dependsOnMethods = "getAuthToken")
    public void createOrder() {
        basePath = BASE_PATH2;

        OrderPojo orderBody = new OrderPojo("CAPTURE", Collections.singletonList(new PurchaseUnitsPojo("USD", "300.00")));

        Response response = given().auth().oauth2(accessToken).contentType(ContentType.JSON).body(orderBody).post(payPalOrderURI);

        log.info("{} Request URI was send: {}", RequestTypes.POST.getValue(), baseURI + basePath + payPalOrderURI);
        log.info("Response is: \n{}", response.asPrettyString());

        if (response.statusCode() == StatusCodes.CREATED.getValue()) {
            createdOrderId = response.jsonPath().getString("id");
            log.info("New order ID is: {}", createdOrderId);
            assertThat(response.jsonPath().getString("status")).isEqualTo(CREATED_STATUS);
        } else {
            throw new RuntimeException("Order was NOT created!");
        }
    }

    @Test(priority = 2, dependsOnMethods = "getAuthToken")
    public void getOrder() {
        basePath = BASE_PATH2;

        Response response = given().auth().oauth2(accessToken).get(payPalOrderURI + "/" + createdOrderId);

        log.info("{} Request URI was send: {}", RequestTypes.POST.getValue(), payPalOrderURI + "/" + createdOrderId);
        log.info("Response is: \n{}", response.asPrettyString());

        if (response.statusCode() == StatusCodes.OK.getValue()) {
            assertThat(response.jsonPath().getString("id")).isEqualTo(createdOrderId);
            assertThat(response.jsonPath().getString("status")).isEqualTo(CREATED_STATUS);
        } else {
            throw new RuntimeException("Order was NOT gotten successfully!");
        }
    }
}
