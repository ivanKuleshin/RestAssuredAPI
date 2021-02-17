package com.erat.RestAssuredAPI.setUp;

import com.erat.RestAssuredAPI.utils.RestClient;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class StripeBaseTest extends BaseTest {
    protected static String customerAPIEndPoint;
    protected static String validSecretKey;
    protected static RequestSpecification requestSpecification;
    protected static RestClient restClient = new RestClient();

    @BeforeSuite
    public void setUp() {
        super.setUp();
        RestAssured.baseURI = properties.getProperty("baseStripeURI");
        RestAssured.basePath = properties.getProperty("baseStripePath");

        customerAPIEndPoint = properties.getProperty("customerAPIEndPoint");
        validSecretKey = properties.getProperty("validSecretKey");
        excelBaseDir = properties.getProperty("excelBaseDir");

        System.setProperty("current.date", date);
    }

    protected void setRequestSpecification() {
        requestSpecification = given().auth().oauth2(validSecretKey);
    }
}
