package com.example.payoneer.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This service generator creates the retrofit need to hit the api endpoint
 */
public class ServiceGenerator {
    private static final String BASE_URL = "https://raw.githubusercontent.com/optile/checkout-android/develop/";

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = builder.build();

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <T> T createService(
            Class<T> serviceClass
    ) {
        if(!httpClient.interceptors().contains(logging)){
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }

}
