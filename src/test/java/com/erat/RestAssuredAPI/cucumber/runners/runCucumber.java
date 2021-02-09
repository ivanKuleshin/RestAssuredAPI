package com.erat.RestAssuredAPI.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/createOrder.feature"},
        glue = {"com.erat.RestAssuredAPI.cucumber.definitionSteps"})
public class runCucumber extends AbstractTestNGCucumberTests {

}
