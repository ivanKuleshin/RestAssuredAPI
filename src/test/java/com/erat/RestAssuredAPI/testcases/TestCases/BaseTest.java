package com.erat.RestAssuredAPI.testcases.TestCases;
import org.testng.annotations.*;

public class BaseTest {
    @BeforeSuite
    public void setUp() {
        System.out.println("Initialization... ");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("Closing...");
    }
}