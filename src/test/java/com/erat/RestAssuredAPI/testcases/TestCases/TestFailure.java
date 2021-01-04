package com.erat.RestAssuredAPI.testcases.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFailure extends BaseTest {

    @Test
    public void doLogin() {
        Assert.fail("Failing the test");
    }
}