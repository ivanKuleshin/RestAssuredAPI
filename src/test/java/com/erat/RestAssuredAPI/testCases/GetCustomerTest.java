package com.erat.RestAssuredAPI.testCases;

import com.erat.RestAssuredAPI.APIs.CreateCustomerAPI;
import com.erat.RestAssuredAPI.APIs.GetCustomerAPI;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

public class GetCustomerTest extends BaseTest {
    private static final CreateCustomerAPI createCustomerAPI =  new CreateCustomerAPI();
    private static final GetCustomerAPI getCustomerAPI1 =  new GetCustomerAPI();

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void getCustomerById(Map<String, Object> testDataMap){
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        response = getCustomerAPI1.sendGetRequestToRetrieveCustomer(response.jsonPath().get("id"));
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        testUtil.validateCustomerResponse(testDataMap, response);
    }
}
