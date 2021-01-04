package com.erat.RestAssuredAPI.testcases.ResrAssuredExamples;

import java.util.List;

public class UserPOJO {
    private String email;
    private String firstName;
    private String lastName;
    private List<Integer> listOfPhoneNumbers;
    private AddressPOJO address;

    public UserPOJO(String email, String firstName, String lastName, List<Integer> listOfPhoneNumbers, AddressPOJO address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.listOfPhoneNumbers = listOfPhoneNumbers;
        this.address = address;
    }

    public AddressPOJO getAddress() {
        return address;
    }

    public void setAddress(AddressPOJO address) {
        this.address = address;
    }

    public void setList(List<Integer> listOfPhoneNumbers) {
        this.listOfPhoneNumbers = listOfPhoneNumbers;
    }

    public List<Integer> getList() {
        return listOfPhoneNumbers;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
