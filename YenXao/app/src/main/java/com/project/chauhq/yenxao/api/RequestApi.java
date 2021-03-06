package com.project.chauhq.yenxao.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author ChauHQ
 */
public class RequestApi {
    public static final String API_BASE_URL = "http://maps.googleapis.com";
    public static final String API_HOME_URL = "http://172.16.40.245/yenxao/api";

    public static ApiClient requestAdressApi() {
        OkHttpClient httpClient = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(ApiClient.class);
    }

    public static ApiClient requestHomeApi() {
        OkHttpClient httpClient = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.40.245")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(ApiClient.class);
    }

    public static ApiClient requestGoogleAPi() {
        OkHttpClient httpClient = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(ApiClient.class);
    }
}
