package com.erat.RestAssuredAPI.setUp;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

public class PayPalBaseTest extends BaseTest{
    protected static String payPalUserName;
    protected static String payPalPassword;
    protected static String getPayPalTokenURI;
    protected static String payPalOrderURI;
    protected static String basePayPalURI;

    @BeforeSuite
    public void setUp() {
        payPalUserName = properties.getProperty("payPalUserName");
        payPalPassword = properties.getProperty("payPalPassword");
        payPalOrderURI = properties.getProperty("payPalOrderURI");
        getPayPalTokenURI = properties.getProperty("getPayPalTokenURI");
        basePayPalURI = properties.getProperty("basePayPalURI");
        loggingFilePath = properties.getProperty("loggingFilePath");

        PropertyConfigurator.configure(loggingFilePath);
    }
}
