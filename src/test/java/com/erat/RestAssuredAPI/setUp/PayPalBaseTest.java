package com.erat.RestAssuredAPI.setUp;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

public class PayPalBaseTest extends BaseTest{
    protected static String payPalUserName;
    protected static String payPalPassword;
    protected static String getPayPalTokenURI;
    protected static String payPalOrderURI;
    protected static String accessToken;

    protected final static String BASE_PATH1 = "/v1";
    protected final static String BASE_PATH2 = "/v2";

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = properties.getProperty("basePayPalURI");

        payPalUserName = properties.getProperty("payPalUserName");
        payPalPassword = properties.getProperty("payPalPassword");
        payPalOrderURI = properties.getProperty("payPalOrderURI");
        getPayPalTokenURI = properties.getProperty("getPayPalTokenURI");
        loggingFilePath = properties.getProperty("loggingFilePath");

        PropertyConfigurator.configure(loggingFilePath);
    }
}