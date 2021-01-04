package com.erat.RestAssuredAPI.setUp;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected static final String INVALID_EXPECTED_TYPE = "invalid_request_error";

    public static Properties properties = new Properties();

    public static String customerAPIEndPoint = "customerAPIEndPoint";
    public static String validSecretKey = "validSecretKey";
    public static String excelBaseDir = "excelBaseDir";

    public enum StatusCodes {
        OK(200),
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        INTERNAL_SERVER_ERROR(500),
        NOT_FOUND(404);
        private final int statusCode;
        StatusCodes(int statusCode) {
            this.statusCode = statusCode;
        }
        public int getValue(){ return statusCode;}
    }

    public enum RequestTypes {
        GET("get"),
        POST("post"),
        PUT("put"),
        DELETE("delete");
        private final String requestType;
        RequestTypes(String requestType) {
            this.requestType = requestType;
        }
        public String getValue(){ return requestType;}
    }

    @BeforeSuite
    public void setUp() {
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/properties/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAssured.baseURI = properties.getProperty("baseURI");
        RestAssured.basePath = properties.getProperty("basePath");
    }

    @AfterSuite
    public void tearDown() {

    }
}