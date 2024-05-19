package com.erat.RestAssuredAPI.cucumber.definitionSteps;

import com.erat.RestAssuredAPI.APIs.paypal.PayPalAPI;
import com.erat.RestAssuredAPI.cucumber.utils.ContextKeys;
import com.erat.RestAssuredAPI.cucumber.utils.ScenarioContext;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PayPalStepDefinitions extends BaseDefinitionSteps {
    private final ScenarioContext scenarioContext;
    private static final PayPalAPI payPalAPI = new PayPalAPI();

    private static final String EXCEL_BASE_DIR = properties.getProperty("excelBaseDir");
    private static final String CREATED_STATUS = "CREATED";

    public PayPalStepDefinitions() {
        this.scenarioContext = new ScenarioContext();
    }

    @Given("I have the test data for creating an order")
    public void i_have_the_test_data_for_creating_an_order() {
        Map<String, String> testDataMap = getTestData("PayPalTestData.xlsx", "createOrder");
        scenarioContext.setContext(ContextKeys.CREATE_ORDER_TEST_DATA, testDataMap);
    }

    @When("I send a request to create an order")
    public void i_send_a_request_to_create_an_order() {
        Map<String, String> testDataMap = scenarioContext.getContext(ContextKeys.CREATE_ORDER_TEST_DATA);
        Response response = payPalAPI.createOrder(testDataMap);

        if (response.statusCode() == BaseTest.StatusCodes.CREATED.getValue()) {
            String createdOrderId = response.jsonPath().getString("id");
            scenarioContext.setContext(ContextKeys.CREATED_ORDER_ID, createdOrderId);
            assertThat(response.jsonPath().getString("status")).isEqualTo(CREATED_STATUS);
        } else {
            throw new RuntimeException("Order was NOT created!");
        }
    }

    @Then("The order is created successfully")
    public void theOrderIsCreatedSuccessfully() {
        String createdOrderId = scenarioContext.getContext(ContextKeys.CREATED_ORDER_ID);
        assertThat(createdOrderId).isNotNull().isNotEmpty();
    }

    @And("I send a request to get the order")
    public void iSendARequestToGetTheOrder() {
        String createdOrderId = scenarioContext.getContext(ContextKeys.CREATED_ORDER_ID);
        Response response = payPalAPI.getOrder(createdOrderId);
        assertThat(response.statusCode()).isEqualTo(BaseTest.StatusCodes.OK.getValue());

        scenarioContext.setContext(ContextKeys.ORDER_DETAILS, response.jsonPath().getList("purchase_units"));
    }

    @Then("The order is retrieved successfully")
    public void theOrderIsRetrievedSuccessfully() {
        List<Map<String, Object>> orderDetails = scenarioContext.getContext(ContextKeys.ORDER_DETAILS);
        assertThat(orderDetails).isNotNull().isNotEmpty();
        assertThat(orderDetails).hasSize(1);
    }

    @Given("I have the test data for creating an order with invalid data")
    public void iHaveTheTestDataForCreatingAnOrderWithInvalidData() {
        Map<String, String> testDataMap = getTestData("PayPalTestData.xlsx", "createInvalidOrder");
        scenarioContext.setContext(ContextKeys.CREATE_INVALID_ORDER_TEST_DATA, testDataMap);
    }

    @Then("The order is not created successfully")
    public void theOrderIsNotCreatedSuccessfully() {
        Response response = scenarioContext.getContext(ContextKeys.RESPONSE, Response.class);
        assertThat(response.statusCode()).isEqualTo(BaseTest.StatusCodes.BAD_REQUEST.getValue());
        assertThat(response.jsonPath().getString("name")).isEqualTo("INVALID_REQUEST");
    }

    private Map<String, String> getTestData(String fileName, String sheetName) {
        ExcelReader excelReader = new ExcelReader(EXCEL_BASE_DIR + fileName);
        int rows = excelReader.getRowCount(sheetName);
        int columns = excelReader.getColumnCount(sheetName);

        if (rows == -1 || columns == -1) {
            throw new RuntimeException(String.format("%s sheetName was NOT found!", sheetName));
        }

        Map<String, String> testDataMap = new HashMap<>();
        for (int colNum = 0; colNum < columns; colNum++) {
            testDataMap.put(excelReader.getCellData(sheetName, colNum, 0), excelReader.getCellData(sheetName, colNum, 1));
        }
        return testDataMap;
    }

    @When("I send a request to create an invalid order")
    public void iSendARequestToCreateAnInvalidOrder() {
        Map<String, String> testDataMap = scenarioContext.getContext(ContextKeys.CREATE_INVALID_ORDER_TEST_DATA);
        Response response = payPalAPI.createOrder(testDataMap);
        scenarioContext.setContext(ContextKeys.RESPONSE, response);
    }
}