package com.example.population_census.api;

import com.example.population_census.api.request.DetailsRequest;
import com.example.population_census.api.request.LoginRequest;
import com.example.population_census.api.request.RegisterRequest;
import com.example.population_census.api.response.AuthResponse;


import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;


public interface ApiService {
    //Auth
    @POST("/login")
    Call<AuthResponse> loginUser(@Body LoginRequest authRequest);

    @POST("/register")
    Call<AuthResponse> registerUser(@Body RegisterRequest authRequest);


    @POST("/user_details")
    Call<String> uploadDetails(@Body DetailsRequest detailsRequest);




}


