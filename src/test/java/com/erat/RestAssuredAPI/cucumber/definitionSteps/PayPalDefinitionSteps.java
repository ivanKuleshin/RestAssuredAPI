package com.erat.RestAssuredAPI.cucumber.definitionSteps;

import com.erat.RestAssuredAPI.APIs.paypal.PayPalAPI;
import com.erat.RestAssuredAPI.setUp.PayPalBaseTest;
import com.erat.RestAssuredAPI.testCases.paypal.PayPalTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;

import java.util.Map;

@Slf4j
public class PayPalDefinitionSteps extends PayPalBaseTest{

    private static final PayPalTest payPalTest = new PayPalTest();

    @Before
    public void setUp(){
        super.setUp();
    }

    @Given("User creates order with currency code {string}, value {string} and intent {string}")
    public void createOrder(String currencyCode, String currencyValue, String intent){
        payPalTest.createOrder(Map.of("intent", intent, "currency_code", currencyCode, "value", currencyValue));
    }

    @Given("User gets order by ID")
    public void getOrder(){
        payPalTest.getOrder();
    }
}
