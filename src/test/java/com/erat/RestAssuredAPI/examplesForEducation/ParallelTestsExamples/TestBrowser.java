package com.erat.RestAssuredAPI.examplesForEducation.ParallelTestsExamples;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;

@Feature("Parallel tests")
public class TestBrowser extends BaseTest {
    @Parameters("paramFromXml")
    @Test(groups = "parallel")
    @Story("Parallel tests - parameter from XML")
    public void parallelTestExecutionTest(@Optional("Default value") String paramFromXml) throws InterruptedException {
        Date date = new Date();
        System.out.println("Parameter for the test (1): " + paramFromXml + " --- " + date);
        Thread.sleep(2000);
    }

    @Test(dataProvider = "getData", groups = "parallel")
    @Story("Parallel tests - data provider - getData")
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