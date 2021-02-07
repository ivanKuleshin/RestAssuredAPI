package com.erat.RestAssuredAPI.testCases.stripe;

import com.erat.RestAssuredAPI.APIs.stripe.CreateCustomerAPI;
import com.erat.RestAssuredAPI.APIs.stripe.DeleteCustomerAPI;
import com.erat.RestAssuredAPI.APIs.stripe.GetCustomerAPI;
import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.utils.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GetCustomerTest extends BaseTest {
    private static final CreateCustomerAPI createCustomerAPI =  new CreateCustomerAPI();
    private static final GetCustomerAPI getCustomerAPI =  new GetCustomerAPI();
    private static final DeleteCustomerAPI deleteCustomerAPI = new DeleteCustomerAPI();

    private static final String NO_SUCH_CUSTOMER_MSG = "No such customer: '%s'";
    private static final int DEFAULT_NUMBER_OF_CUSTOMERS = 10;

    @Test(dataProviderClass = DataUtil.class, dataProvider = "getExcelDataAsTableWithOneSheet")
    public void getCustomerById(Map<String, Object> testDataMap){
        Response response = createCustomerAPI.sendPostRequestToCreateCustomer(testDataMap);
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        String customerId = response.jsonPath().get("id");
        response = getCustomerAPI.sendGetRequestToRetrieveCustomer(customerId);
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());
        testUtil.validateCustomerResponse(testDataMap, response);

        deleteCustomerAPI.sendDeleteRequestToDeleteCustomer(customerId);
    }

    @Test
    public void getCustomerByInvalidId(){
        String invalidCustomerId = String.valueOf(new Random(11111).nextInt(99999));
        Response response = getCustomerAPI.sendGetRequestToRetrieveCustomer(invalidCustomerId);

        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.NOT_FOUND.getValue());
        assertThat(response.jsonPath().getMap("error").get("message")).isEqualTo(String.format(NO_SUCH_CUSTOMER_MSG, invalidCustomerId));
        assertThat(response.jsonPath().getMap("error").get("type")).isEqualTo(INVALID_EXPECTED_TYPE);
    }

    @Test
    public void getAllCustomers(){
        Response response = getCustomerAPI.sendGetRequestToRetrieveAllCustomers();
        assertThat(response.getStatusCode()).isEqualTo(StatusCodes.OK.getValue());

        List<Object> listOfCustomers = response.jsonPath().get("data");
        assertThat(response.jsonPath().getString("object")).isEqualTo("list");
        assertThat(listOfCustomers.size()).isEqualTo(DEFAULT_NUMBER_OF_CUSTOMERS);
    }
}