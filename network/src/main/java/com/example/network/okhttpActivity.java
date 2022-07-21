package com.example.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class okhttpActivity extends AppCompatActivity {

    private static final String TAG = "okhttpActivity";
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        okHttpClient = new OkHttpClient();
    }

    public void btn_get_syn(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.d(TAG, "btn_post_asy: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void btn_get_asy(View view) {
        Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d(TAG, "btn_get_asy: " + response.body().string());
                }
            }
        });
    }

    public void btn_post_asy(View view) {
        FormBody formBody = new FormBody.Builder().
                add("a", "1").
                add("b", "2").
                build();
        Request request = new Request.Builder().url("https://www.httpbin.org/post").post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d(TAG, "btn_post_asy: " + response.body().string());
                }
            }
        });

    }

    public void btn_post_syn(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder().
                        add("a", "1").
                        add("b", "2").
                        build();
                Request request = new Request.Builder().url("https://www.httpbin.org/post").post(formBody).build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.d(TAG, "btn_post_syn: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}