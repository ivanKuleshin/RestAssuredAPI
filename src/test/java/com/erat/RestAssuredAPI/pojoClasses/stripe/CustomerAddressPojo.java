package com.erat.RestAssuredAPI.pojoClasses.stripe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CustomerAddressPojo {
    private String city;
    private String country;
    private String line1;
    private String line2;
    @JsonProperty("postal_code")
    private String postalCode;
    private String state;

    public CustomerAddressPojo(Object city, Object country, Object line1, Object line2, Object postalCode, Object state) {
        this.city = city.toString();
        this.country = country.toString();
        this.line1 = line1.toString();
        this.line2 = line2.toString();
        this.postalCode = postalCode.toString();
        this.state = state.toString();
    }

    public static Map<String, String> getCustomerAddressAsTestMap(CustomerAddressPojo customerAddressPojo) {
        Map<String, String> customerAddressMap = new HashMap<>();
        customerAddressMap.put("address[city]", customerAddressPojo.getCity());
        customerAddressMap.put("address[country]", customerAddressPojo.getCountry());
        customerAddressMap.put("address[line1]", customerAddressPojo.getLine1());
        customerAddressMap.put("address[line2]", customerAddressPojo.getLine2());
        customerAddressMap.put("address[postal_code]", customerAddressPojo.getPostalCode());
        customerAddressMap.put("address[state]", customerAddressPojo.getState());
        return customerAddressMap;
    }

    public static Map<String, Object> getCustomerAddressAsNormalMap(Map<String, String> testDataMap) {
        Map<String, Object> normalMap = new HashMap<>();
        for(Map.Entry<String, String> entry : testDataMap.entrySet()){
            if(entry.getKey().contains("address")){
                normalMap.put(substringKey(entry), entry.getValue());
            }
        }
        return normalMap;
    }

    private static String substringKey(Map.Entry<String, String> entry){
        return entry.getKey().substring(entry.getKey().indexOf("s[") + 2, entry.getKey().indexOf(']'));
    }

    public static CustomerAddressPojo getDefaultCustomerAddressPojo() {
        return new CustomerAddressPojo("Kyiv", "Ukraine",
                "line1", "line2", "07400", "Kyiv obl.");
    }

    public static CustomerAddressPojo getCustomerAddressPojoFromMap(Map<String, String> testDataMap){
        return new CustomerAddressPojo(testDataMap.get("address[city]"), testDataMap.get("address[country]"),
                testDataMap.get("address[line1]"), testDataMap.get("address[line2]"), testDataMap.get("address[postal_code]"),
                testDataMap.get("address[state]"));
    }

//    public static Map<String, String> getShippingInfoFromTestData(Map<String, Object> testDataMap){
//        Map<String, Object> shippingInfo = new HashMap<>();
//
//    }
}