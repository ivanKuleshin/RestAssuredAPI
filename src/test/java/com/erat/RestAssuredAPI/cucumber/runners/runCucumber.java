package com.erat.RestAssuredAPI.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/"},
        glue = {"com.erat.RestAssuredAPI.cucumber.definitionSteps"},
        plugin = { "pretty", "html:target/cucumber-reports"})
public class runCucumber extends AbstractTestNGCucumberTests {

}
