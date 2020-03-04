package com.example.population_census.api.request;

public class LoginRequest {
    private String email, password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
