package com.erat.RestAssuredAPI.setUp;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

public class StripeBaseTest extends BaseTest{
    protected static String customerAPIEndPoint;
    protected static String validSecretKey;
    protected static String excelBaseDir;

    @BeforeSuite
    public void setUp(){
        RestAssured.baseURI = properties.getProperty("baseStripeURI");
        RestAssured.basePath = properties.getProperty("baseStripePath");

        customerAPIEndPoint = properties.getProperty("customerAPIEndPoint");
        validSecretKey = properties.getProperty("validSecretKey");
        excelBaseDir = properties.getProperty("excelBaseDir");
        loggingFilePath = properties.getProperty("loggingFilePath");

        System.setProperty("current.date", date);
        PropertyConfigurator.configure(loggingFilePath);
    }
}
