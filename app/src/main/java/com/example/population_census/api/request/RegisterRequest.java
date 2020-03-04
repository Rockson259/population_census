package com.example.population_census.api.request;

public class RegisterRequest {
    private String fullName, email, phoneNumber,password;

    public RegisterRequest(String fullName, String email, String phoneNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
