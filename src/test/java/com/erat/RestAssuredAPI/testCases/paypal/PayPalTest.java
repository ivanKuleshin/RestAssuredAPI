package com.erat.RestAssuredAPI.testCases.paypal;

import com.erat.RestAssuredAPI.APIs.paypal.PayPalAPI;
import com.erat.RestAssuredAPI.setUp.PayPalBaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class PayPalTest extends PayPalBaseTest {
    private static String createdOrderId;

    private final static String CREATED_STATUS = "CREATED";

    private static final PayPalAPI payPalAPI = new PayPalAPI();

    @Test
    public void getAuthToken() {
        accessToken = payPalAPI.getAuthKey();
    }

    @Test(priority = 1, dependsOnMethods = "getAuthToken", dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createOrder(Map<String, String> testDataMap) {
        Response response = payPalAPI.createOrder(testDataMap);

        if (response.statusCode() == StatusCodes.CREATED.getValue()) {
            createdOrderId = response.jsonPath().getString("id");
            log.info("New order ID is: {}", createdOrderId);
            assertThat(response.jsonPath().getString("status")).isEqualTo(CREATED_STATUS);
        } else {
            throw new RuntimeException("Order was NOT created!");
        }
    }

    @Test(priority = 2, dependsOnMethods = "createOrder")
    public void getOrder() {
        Response response = payPalAPI.getOrder(createdOrderId);

        if (response.statusCode() == StatusCodes.OK.getValue()) {
            assertThat(response.jsonPath().getString("id")).isEqualTo(createdOrderId);
            assertThat(response.jsonPath().getString("status")).isEqualTo(CREATED_STATUS);
        } else {
            throw new RuntimeException("Order was NOT gotten successfully!");
        }
    }
}
