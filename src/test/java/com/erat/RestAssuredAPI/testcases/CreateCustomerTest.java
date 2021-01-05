package com.erat.RestAssuredAPI.testcases;

import com.erat.RestAssuredAPI.APIs.CreateCustomerAPI;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.erat.RestAssuredAPI.testcases.pojoClasses.CustomerAddressPojo.getCustomerAddressAsNormalMap;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class CreateCustomerTest extends BaseTest {
    private static final CreateCustomerAPI createCustomerAPI =  new CreateCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithValidTokenUsingPojo(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomerUsingPojo(testDataMap);

        SoftAssertions softAssertions  = new SoftAssertions();
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        softAssertions.assertAll();

        Map<String, Object> expectedTestData = testUtil.getExpectedData(testDataMap);
        expectedTestData.put("address", getCustomerAddressAsNormalMap(testDataMap));

        testUtil.actualMapContainsExpected(response.jsonPath().getMap("$"), expectedTestData);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void createCustomerWithInvalidToken(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomerWithInvalidToken(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.UNAUTHORIZED.getValue());
        String actualType = response.jsonPath().getMap("error").get("type").toString();
        assertThat(actualType).isEqualTo(INVALID_EXPECTED_TYPE);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithValidToken2(Map<String, String> testDataMap) {
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTable")
    public void createCustomerWithInvalidToken2(Map<String, String> testDataMap) {
        createCustomerWithInvalidToken(testDataMap);
    }
}