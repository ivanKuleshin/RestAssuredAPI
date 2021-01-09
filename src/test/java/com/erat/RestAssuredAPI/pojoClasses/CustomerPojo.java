package com.erat.RestAssuredAPI.pojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CustomerPojo {
    private String name;
    private String email;
    private String description;
    private String phone;
    @JsonProperty("preferred_locales[0]")
    private String preferredLocales;


    public CustomerPojo(String name, String email, String description, String phone, String preferredLocales) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.phone = phone;
        this.preferredLocales = preferredLocales;
    }

    public static CustomerPojo getDefaultCustomerPojo() {
        return new CustomerPojo("Pojo Class", "restAssured@testmail.com", "Adding a customer using Pojo",
                "+38014881337", "uk");
    }



    public static CustomerPojo getCustomerPojoFromMap(Map<String, String> testDataMap){
        return new CustomerPojo(testDataMap.get("name"), testDataMap.get("email"), testDataMap.get("description"),
                testDataMap.get("phone"), testDataMap.get("preferred_locales[0]"));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getPreferredLocales() {
        return preferredLocales;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPreferredLocales(String preferredLocales) {
        this.preferredLocales = preferredLocales;
    }
}
