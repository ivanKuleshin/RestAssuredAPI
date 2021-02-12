package com.erat.RestAssuredAPI.cucumber.definitionSteps;

import com.erat.RestAssuredAPI.setUp.PayPalBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PayPalDefinitionSteps extends PayPalBaseTest {

    @Given("User gets access token")
    public void getAccessToken() {

    }

    @When("User set currency code as {string} and value as {string}")
    public void setCurrencyCodeAndValue(String currencyCode, String currencyValue) {

    }

    @When("User verify the status is {string}")
    public void verifyStatusCode(String status) {

    }
}
