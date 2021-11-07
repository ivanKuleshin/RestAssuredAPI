package com.erat.RestAssuredAPI.testCases.stripe;

import com.erat.RestAssuredAPI.APIs.stripe.GetCustomerAPI;
import com.erat.RestAssuredAPI.APIs.stripe.UpdateCustomerAPI;
import com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo;
import com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerPojo;
import com.erat.RestAssuredAPI.utils.DataUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo.getCustomerAddressAsNormalMap;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Validation of updating the user")
public class UpdateCustomerTest extends UpdateCustomerAPI {
    private static final GetCustomerAPI getCustomerAPI = new GetCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    @Story("Update customer (happy path)")
    public void updateCustomer(Map<String, String> testDataMap) {
        Response response = getCustomerAPI.sendGetRequestToRetrieveAllCustomers();
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        String customerId = response.jsonPath().getObject(String.format("data[%s]",
                new Random().nextInt(10)), CustomerPojo.class).getId();
        response = sendPostRequestToUpdateCustomer(testDataMap, customerId);
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        CustomerAddressPojo customerAddressPojo = response.jsonPath().getObject("shipping.address", CustomerAddressPojo.class);
        testUtil.actualMapContainsExpected(objectMapper.convertValue(customerAddressPojo,
                new TypeReference<>() {
                }), getCustomerAddressAsNormalMap(testDataMap));
    }
}