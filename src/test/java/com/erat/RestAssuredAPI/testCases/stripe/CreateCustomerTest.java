package com.erat.RestAssuredAPI.testCases.stripe;

import com.erat.RestAssuredAPI.APIs.stripe.CreateCustomerAPI;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class CreateCustomerTest extends StripeBaseTest {
    private static final CreateCustomerAPI createCustomerAPI =  new CreateCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithValidTokenUsingPojo(Map<String, Object> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomerUsingPojo(testDataMap);
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        testUtil.validateCustomerResponse(testDataMap, response);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomerWithInvalidToken(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.UNAUTHORIZED.getValue());
        String actualType = response.jsonPath().getMap("error").get("type").toString();
        assertThat(actualType).isEqualTo(INVALID_EXPECTED_TYPE);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithValidToken2(Map<String, Object> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithInvalidToken2(Map<String, String> testDataMap) {
        createCustomerWithInvalidToken(testDataMap);
    }
}