package com.erat.RestAssuredAPI.testCases.stripe;

import com.erat.RestAssuredAPI.APIs.stripe.CreateCustomerAPI;
import com.erat.RestAssuredAPI.APIs.stripe.DeleteCustomerAPI;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Validation of deletion the user")
public class DeleteCustomerTest extends DeleteCustomerAPI {
    private static final CreateCustomerAPI createCustomerAPI = new CreateCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    @Story("Delete customer")
    public void deleteCustomer(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);

        String customerId = response.jsonPath().get("id");
        response = sendDeleteRequestToDeleteCustomer(customerId);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
        assertThat(response.jsonPath().getString("id")).isEqualTo(customerId);
        assertThat(response.jsonPath().getBoolean("deleted")).isTrue();
    }

    @Test
    @Story("Delete customer with non existing id")
    public void deleteCustomerWithNonExistingId() {
        Response response = sendDeleteRequestToDeleteCustomer(String.valueOf(new Random(100).nextInt(999)));

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.NOT_FOUND.getValue());
        assertThat(response.jsonPath().getMap("error").get("type")).isEqualTo(INVALID_EXPECTED_TYPE);
    }
}
