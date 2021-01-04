package com.erat.RestAssuredAPI.testcases.ParallelTests;

import com.erat.RestAssuredAPI.testcases.TestCases.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;

public class TestBrowser extends BaseTest {
    @Parameters("paramFromXml")
    @Test(groups = "parallel")
    public void parallelTestExecutionTest(@Optional("Default value") String paramFromXml) throws InterruptedException {
        Date date = new Date();
        System.out.println("Parameter for the test (1): " + paramFromXml + " --- " + date);
        Thread.sleep(2000);
    }

    @Test(dataProvider = "getData", groups = "parallel")
    public void parallelTestExecutionTest2(String param) throws InterruptedException {
        Date date = new Date();
        System.out.println("Parameter for the test (2): " + param + " --- " + date);
        Thread.sleep(2000);
    }

    @DataProvider(name = "getData", parallel = true)
    public Object[] getData(){
        Object [] objects = new Object[2];

        objects[0] = "Chrome";
        objects[1] = "FireFox";

        return objects;
    }
}