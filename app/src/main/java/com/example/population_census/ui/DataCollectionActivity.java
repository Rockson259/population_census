package com.example.population_census.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.population_census.R;
import com.example.population_census.api.ApiServiceProvider;
import com.example.population_census.api.request.DetailsRequest;
import com.example.population_census.databinding.ActivityDataCollectionBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCollectionActivity extends AppCompatActivity {

    private ActivityDataCollectionBinding mBinding;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_collection);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Saving details.....");
        mBinding.saveButton.setOnClickListener(view->{

            DetailsRequest detailsRequest = new DetailsRequest();
            detailsRequest.setDigitalAddress(mBinding.digitalAddressEditText.getText().toString());
            detailsRequest.setNationalId(mBinding.nationalIdEditText.getText().toString());
            detailsRequest.setHouseAddress(mBinding.houseAddressEditText.getText().toString());
            uploadDetails(detailsRequest);
        });


    }

    private void uploadDetails(DetailsRequest detailsRequest){
        mProgressDialog.show();
        ApiServiceProvider.getApiService()
                .uploadDetails(detailsRequest)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        mProgressDialog.dismiss();

                        if(response.isSuccessful()){
                            Intent i = new Intent(DataCollectionActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                        else {
                            try {
                                Toast.makeText(DataCollectionActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT)
                                        .show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mProgressDialog.dismiss();
                    }
                });
    }
}
