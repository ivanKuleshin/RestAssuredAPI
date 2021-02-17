package com.erat.RestAssuredAPI.setUp;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class StripeBaseTest extends BaseTest{
    protected static String customerAPIEndPoint;
    protected static String validSecretKey;

    @BeforeSuite
    public void setUp(){
        super.setUp();
        RestAssured.baseURI = properties.getProperty("baseStripeURI");
        RestAssured.basePath = properties.getProperty("baseStripePath");

        customerAPIEndPoint = properties.getProperty("customerAPIEndPoint");
        validSecretKey = properties.getProperty("validSecretKey");
        excelBaseDir = properties.getProperty("excelBaseDir");

        System.setProperty("current.date", date);
    }
}
