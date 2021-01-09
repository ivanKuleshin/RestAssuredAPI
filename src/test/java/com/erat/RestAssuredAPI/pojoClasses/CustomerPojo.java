package com.erat.RestAssuredAPI.pojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPojo implements Serializable {
    private String id;
    private String name;
    private String email;
    private String description;
    private String phone;
    @JsonProperty("preferred_locales[0]")
    private String preferredLocales;


    public CustomerPojo(Object name, Object email, Object description, Object phone, Object preferredLocales) {
        this.name = name.toString();
        this.email = email.toString();
        this.description = description.toString();
        this.phone = phone.toString();
        this.preferredLocales = preferredLocales.toString();
    }

    public static CustomerPojo getDefaultCustomerPojo() {
        return new CustomerPojo("Pojo Class", "restAssured@testmail.com", "Adding a customer using Pojo",
                "+38014881337", "uk");
    }

    public static CustomerPojo getCustomerPojoFromMap(Map<String, Object> testDataMap){
        return new CustomerPojo(testDataMap.get("name"), testDataMap.get("email"), testDataMap.get("description"),
                testDataMap.get("phone"), testDataMap.get("preferred_locales[0]"));
    }
}