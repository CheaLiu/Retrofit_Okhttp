package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qi.ResponseObserver;
import com.qi.http.RetrofitManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class MainActivity extends AppCompatActivity {

    public static interface ITest {
        @POST("api/AddressList/DoloadAddressList")
        Observable<TestBean> test(@Body PostBean postBean);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        RetrofitManager retrofitManager = new RetrofitManager.Builder()
                .baseUrl("http://proapp.feikongbao.com/")
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        ITest test = retrofitManager.create(ITest.class);
        PostBean postBean = new PostBean();
        postBean.Content = new PostBean.ContentBean();
        postBean.Head = new PostBean.HeadBean();
        postBean.Content.Page = 1;
        postBean.Content.PageSize = 20;
        postBean.Head.pws = "F7D73952C7FC4B889E7DD3F9B9893CC0";
        postBean.Head.uid = "F7D73952C7FC4B889E7DD3F9B9893CC0";
        test.test(postBean).subscribe(new MyResponseObserver());
    }

    public static class MyResponseObserver extends ResponseObserver<TestBean> {

        @Override
        public void onNext(Object o) {
            super.onNext(o);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }
    }
}
