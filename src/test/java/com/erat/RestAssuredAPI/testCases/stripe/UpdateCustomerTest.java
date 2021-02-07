package com.erat.RestAssuredAPI.testCases.stripe;

import com.erat.RestAssuredAPI.APIs.stripe.GetCustomerAPI;
import com.erat.RestAssuredAPI.APIs.stripe.UpdateCustomerAPI;
import com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo;
import com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerPojo;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.erat.RestAssuredAPI.pojoClasses.stripe.CustomerAddressPojo.getCustomerAddressAsNormalMap;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.Random;

public class UpdateCustomerTest extends StripeBaseTest {
    private static final GetCustomerAPI getCustomerAPI = new GetCustomerAPI();
    private static final UpdateCustomerAPI updateCustomerAPI = new UpdateCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void updateCustomer(Map<String, Object> testDataMap) {
        Response response = getCustomerAPI.sendGetRequestToRetrieveAllCustomers();
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        String customerId = response.jsonPath().getObject(String.format("data[%s]",
                new Random().nextInt(10)), CustomerPojo.class).getId();
        response = updateCustomerAPI.sendPostRequestToUpdateCustomer(testDataMap, customerId);
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        CustomerAddressPojo customerAddressPojo = response.jsonPath().getObject("shipping.address", CustomerAddressPojo.class);
        testUtil.actualMapContainsExpected(objectMapper.convertValue(customerAddressPojo,
                new TypeReference<>() {
                }), getCustomerAddressAsNormalMap(testDataMap));
    }
}