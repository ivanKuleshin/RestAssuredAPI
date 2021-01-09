package com.erat.RestAssuredAPI.testCases.xmlRunsTest;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

public class TestCase1 extends BaseTest {
    @BeforeTest
    public void creatingDbConnection() {
        System.out.println("Creating DB connection");
    }

    @AfterTest
    public void closingDbConnection() {
        System.out.println("Closing DB connection");
    }

    @BeforeMethod
    public void launchBrowser() {
        System.out.println("launching browser");
    }

    @AfterMethod()
    public void closeBrowser() {
        System.out.println("Closing browser");
    }

    @Test(priority = 1, groups = {"functional", "smokeTests"})
    public void userReg() {
        System.out.println("User registration");
        Assert.fail("Fail registration");
    }

    @Test(priority = 2, dependsOnMethods = "userReg", groups = "functional")
    public void doLogin() {
        System.out.println("Executing login test");
    }

    @Parameters("paramFromXml")
    @Test(dependsOnMethods = "userReg", alwaysRun = true, groups = "smokeTests")
    public void alwaysRunTest(@Optional("Default value") String paraFromXml) {
        System.out.println("This is \"always run\" test launch");
        System.out.println(paraFromXml);
    }

    @Test
    public void skipTheTest(){
        throw new SkipException("Just Skipp The Test");
    }
}