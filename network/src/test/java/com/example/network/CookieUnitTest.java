package com.example.network;

import androidx.annotation.NonNull;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CookieUnitTest {
    Map<String, List<Cookie>> cookies = new HashMap<>();

    @Test
    public void CookieUnitTest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
            cookieJar(new CookieJar() {
            @NonNull
            @Override
            public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                List<Cookie> cookies = CookieUnitTest.this.cookies.get(httpUrl.host());
                return cookies == null ? new ArrayList<>() : cookies;
            }

            @Override
            public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                cookies.put(httpUrl.host(), list);
            }
        }).
        build();
        FormBody build = new FormBody.Builder().add("username", "sujin").
                add("password", "123456").build();
        Request request = new Request.Builder().url("https://www.wanandroid.com/user/login").post(build).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
//            System.out.println(response.isSuccessful());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        request = new Request.Builder().url("https://www.wanandroid.com/lg/collect/list/0/json").build();
        call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
