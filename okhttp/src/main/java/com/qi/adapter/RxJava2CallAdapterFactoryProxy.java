package com.qi.adapter;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Scheduler;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxJava2CallAdapterFactoryProxy extends CallAdapter.Factory {

    private RxJava2CallAdapterFactory factory;

    private RxJava2CallAdapterFactoryProxy(RxJava2CallAdapterFactory factory) {
        this.factory = factory;
    }

    public static RxJava2CallAdapterFactoryProxy create() {
        return new RxJava2CallAdapterFactoryProxy(RxJava2CallAdapterFactory.create());
    }

    public static RxJava2CallAdapterFactoryProxy createWithScheduler(Scheduler scheduler) {
        if (scheduler == null) throw new NullPointerException("scheduler == null");
        return new RxJava2CallAdapterFactoryProxy(RxJava2CallAdapterFactory.createWithScheduler(scheduler));
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new CallAdapterProxy(factory.get(returnType, annotations, retrofit));
    }
}
