package com.example.population_census.api;



import com.example.population_census.App;
import com.example.population_census.AppConstants;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {


    private static ApiService apiService;

    private ApiServiceProvider() {}
    public static ApiService getApiService() {
        if(apiService == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(1, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(1, TimeUnit.MINUTES) // write timeout
                    .readTimeout(1, TimeUnit.MINUTES); // read timeout
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("user_id", App.getCurrentUserId());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            httpClient.addInterceptor(interceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
            apiService = retrofit.create(ApiService.class);



        }

        return apiService;
    }
}
