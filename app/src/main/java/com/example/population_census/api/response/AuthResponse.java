package com.example.population_census.api.response;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("id")
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
