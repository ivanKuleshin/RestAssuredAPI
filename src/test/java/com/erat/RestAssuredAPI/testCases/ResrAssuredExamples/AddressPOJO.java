package com.erat.RestAssuredAPI.testCases.ResrAssuredExamples;

public class AddressPOJO {
    private String flatNo;
    private String city;
    private String state;
    private String country;

    public AddressPOJO(String flatNo, String city, String state, String country) {
        this.flatNo = flatNo;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}