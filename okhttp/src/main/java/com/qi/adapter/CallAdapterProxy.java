package com.qi.adapter;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class CallAdapterProxy implements CallAdapter {

    private final CallAdapter callAdapter;

    CallAdapterProxy(CallAdapter callAdapter) {
        this.callAdapter = callAdapter;
    }

    @Override
    public Type responseType() {
        return callAdapter.responseType();
    }

    @Override
    public Object adapt(Call call) {
        Object o = callAdapter.adapt(call);
        if (o instanceof Observable) {
            o = ((Observable) o)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        return o;
    }
}
