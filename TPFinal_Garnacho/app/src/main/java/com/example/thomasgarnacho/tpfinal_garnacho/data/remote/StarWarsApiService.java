package com.example.thomasgarnacho.tpfinal_garnacho.data.remote;

import com.example.thomasgarnacho.tpfinal_garnacho.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thomasgarnacho on 11/01/2018.
 */

public class StarWarsApiService {

    private static final StarWarsInterface instance = build();

    public static StarWarsInterface getInstance() {
        return instance;
    }

    private StarWarsApiService() {
    }

    private static StarWarsInterface build() {
        final Gson gson = new GsonBuilder().create(); // JSON deserializer/serializer

        // Create the OkHttp Instance
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(final Chain chain) throws IOException {
                        final Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(StarWarsInterface.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(StarWarsInterface.class);
    }
}
