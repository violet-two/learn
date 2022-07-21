package com.example.network;

import android.util.Log;

import androidx.annotation.NonNull;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InterceptorUnitTest {
    @Test
    public void InterceptorUnitTest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                cache(new Cache(new File("G:\\cache\\learnCache"),1024*1024)).
                addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                //前置处理
                Request request = chain.request().newBuilder().
                        addHeader("os", "android").
                        addHeader("version", "1.0").
                        build();
                Response response = chain.proceed(request);
                //后置处理
                return response;

            }
        }).addNetworkInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                System.out.println(chain.request().header("version"));
                return chain.proceed(chain.request());
            }
        }).build();
        Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
