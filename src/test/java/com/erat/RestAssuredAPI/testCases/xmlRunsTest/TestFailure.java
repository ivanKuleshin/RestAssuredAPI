package com.erat.RestAssuredAPI.testCases.xmlRunsTest;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFailure extends BaseTest {

    @Test
    public void doLogin() {
        Assert.fail("Failing the test");
    }
}