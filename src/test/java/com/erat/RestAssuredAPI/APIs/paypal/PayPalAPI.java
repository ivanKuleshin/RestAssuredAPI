package com.erat.RestAssuredAPI.APIs.paypal;

import com.erat.RestAssuredAPI.pojoClasses.paypal.OrderPojo;
import com.erat.RestAssuredAPI.pojoClasses.paypal.PurchaseUnitsPojo;
import com.erat.RestAssuredAPI.setUp.PayPalBaseTest;
import com.erat.RestAssuredAPI.utils.RestClient;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class PayPalAPI extends PayPalBaseTest {

    private static final RestClient restClient = new RestClient();

    public Response createOrder(Map<String, String> testDataMap) {
        OrderPojo orderBody = new OrderPojo(testDataMap.get("intent"),
                Collections.singletonList(new PurchaseUnitsPojo(testDataMap.get("currency_code"), testDataMap.get("value"))));

        return restClient.sendRequestWithAuthorization(RequestTypes.POST, orderBody,
                basePayPalURI + payPalOrderURI);
    }

    public Response getOrder(final String createdOrderId){
        return restClient.sendRequestWithAuthorization(RequestTypes.GET, basePayPalURI + payPalOrderURI + "/" + createdOrderId);
    }
}
