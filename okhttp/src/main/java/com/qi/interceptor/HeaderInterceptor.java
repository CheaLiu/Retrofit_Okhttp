package com.qi.interceptor;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private InterceptorListener listener;

    public void setListener(InterceptorListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers.Builder builder = request.headers().newBuilder();
        if (listener != null) {
            listener.handleHeader(builder);
        }
        request.newBuilder().headers(builder.build());
        return chain.proceed(request);
    }

    public static interface InterceptorListener {
        void handleHeader(Headers.Builder builder);
    }
}
