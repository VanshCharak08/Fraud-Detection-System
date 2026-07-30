package com.finance.frauddetection.model;

public class Customer {
    int id;
    String name;
    String accountNumber;
    String registeredCountry;

    public Customer(int id, String name, String accountNumber, String registeredCountry) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.registeredCountry = registeredCountry;
    }

    void getDetails(){}
    void setDetails(){}

}
