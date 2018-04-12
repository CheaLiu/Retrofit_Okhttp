package com.qi.http;

import com.qi.adapter.RxJava2CallAdapterFactoryProxy;
import com.qi.interceptor.HeaderInterceptor;
import com.qi.log.HttpLogger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Magnet {
    private final String baseUrl;
    HeaderInterceptor headerInterceptor = new HeaderInterceptor();
    Retrofit retrofit;

    public Magnet(String baseUrl) {
        this.baseUrl = baseUrl;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLogger()))
//                .connectTimeout(10000, TimeUnit.MICROSECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .validateEagerly(true)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactoryProxy.create())
                .client(client)
                .build();
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
