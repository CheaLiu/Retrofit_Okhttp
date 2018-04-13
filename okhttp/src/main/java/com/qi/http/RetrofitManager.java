package com.qi.http;

import com.qi.adapter.RxJava2CallAdapterFactoryProxy;
import com.qi.log.HttpLogger;

import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private Retrofit retrofit;

    public RetrofitManager(String baseUrl, OkHttpClient client) {
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

    public static class Builder {

        protected OkHttpClient.Builder clientBuilder;
        private String baseUrl;

        public Builder() {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(httpLoggingInterceptor);
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(long timeout, TimeUnit timeUnit) {
            clientBuilder.connectTimeout(timeout, timeUnit);
            return this;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            clientBuilder.addNetworkInterceptor(interceptor);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            clientBuilder.addInterceptor(interceptor);
            return this;
        }

        public Builder authenticator(Authenticator authenticator) {
            clientBuilder.authenticator(authenticator);
            return this;
        }

        public Builder cache(Cache cache) {
            clientBuilder.cache(cache);
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar) {
            clientBuilder.cookieJar(cookieJar);
            return this;
        }

        public Builder readTimeout(long timeout, TimeUnit timeUnit) {
            clientBuilder.readTimeout(timeout, timeUnit);
            return this;
        }

        public Builder writeTimeout(long timeout, TimeUnit timeUnit) {
            clientBuilder.writeTimeout(timeout, timeUnit);
            return this;
        }

        public RetrofitManager build() {
            return new RetrofitManager(baseUrl, clientBuilder.build());
        }
    }
}
