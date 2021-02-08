package com.erat.RestAssuredAPI.setUp;

import com.erat.RestAssuredAPI.utils.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    protected static final String INVALID_EXPECTED_TYPE = "invalid_request_error";
    protected static String loggingFilePath;
    protected static String excelBaseDir;

    public static Properties properties = new Properties();
    protected static TestUtil testUtil = new TestUtil();
    protected final ObjectMapper objectMapper = new ObjectMapper();

    String date = new Date().toString().replace(":", "_").replace(" ", "_");

    public enum StatusCodes {
        OK(200),
        CREATED(201),
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

    public BaseTest() {
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/properties/config.properties");
            properties.load(inputStream);

            excelBaseDir = properties.getProperty("excelBaseDir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {

    }
}