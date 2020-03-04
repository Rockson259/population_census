package com.example.population_census.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.population_census.App;
import com.example.population_census.R;
import com.example.population_census.api.ApiServiceProvider;
import com.example.population_census.api.request.RegisterRequest;
import com.example.population_census.api.response.AuthResponse;
import com.example.population_census.databinding.ActivityRegisterBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mBinding;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Creating account....");
        mBinding.registerButton.setOnClickListener(view -> {

            String email = mBinding.emailEditText.getText().toString();
            String password = mBinding.passwordEditText.getText().toString();
            String fullName = mBinding.nameEditText.getText().toString();
            String phoneNumber = mBinding.phoneNumberEditText.getText().toString();

            String errorMessage = "";

            if (fullName.isEmpty()) errorMessage = "name";
            else if (email.isEmpty()) errorMessage = "email";
            else if (password.isEmpty()) errorMessage = "password";
            else if (phoneNumber.isEmpty()) errorMessage = "phone number";

            if (!errorMessage.isEmpty()) {
                Toast.makeText(this, errorMessage + " is empty. Try again!", Toast.LENGTH_SHORT).show();
                return;
            }

            registerUser(new RegisterRequest(fullName, email, phoneNumber, password));
        });
    }

    private void registerUser(RegisterRequest registerRequest) {

        mProgressDialog.show();
        ApiServiceProvider.getApiService()
                .registerUser(registerRequest)
                .enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        mProgressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,
                                    "Registration Successful", Toast.LENGTH_SHORT)
                                    .show();

                            App.setCurrentUserId(response.body().getUserId());
                            Intent i = new Intent(RegisterActivity.this, DataCollectionActivity.class);
                            RegisterActivity.this.startActivity(i);


                        } else {

                            try {
                                Toast.makeText(RegisterActivity.this,
                                        response.errorBody().string(),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        mProgressDialog.dismiss();
                    }
                });
    }
}
