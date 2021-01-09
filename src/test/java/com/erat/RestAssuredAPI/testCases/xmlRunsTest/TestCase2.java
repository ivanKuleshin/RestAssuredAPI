package com.erat.RestAssuredAPI.testCases.xmlRunsTest;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import org.assertj.core.api.*;
import org.testng.annotations.Test;

public class TestCase2 extends BaseTest {
    @Test(groups = "smokeTests")
    public void validateTitles() {
        SoftAssertions softAssertions = new SoftAssertions();

        String expectedTitle = "expected";
        String actualTitle = "actual";
        softAssertions.assertThat(expectedTitle).isEqualTo(actualTitle);
        System.out.println("Hello world!");

        softAssertions.assertAll();
    }
}