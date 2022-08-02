package com.example.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.network.api.API;
import com.example.network.domain.CommandItem;
import com.example.network.domain.GetWithParamsResult;
import com.example.network.domain.PostFileResult;
import com.example.network.domain.PostWithParamsResult;
import com.example.network.utils.RetrofitManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "RequestActivity";
    private API api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        api = RetrofitManager.getRetrofit().create(API.class);
    }

    public void getWithParams(View view) {
        Call<GetWithParamsResult> task = api.getWithParams("我是关键字", 10, "0");
        task.enqueue(new Callback<GetWithParamsResult>() {
            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                Log.d(TAG, "code: "+response.code());
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t);
            }
        });

    }

    public void getWithParams_Query_map(View view) {;
        Map<String,Object> params = new HashMap<>();
        params.put("keyword","我是关键字");
        params.put("page",10);
        params.put("order","0");
        Call<GetWithParamsResult> task = api.getWithParamsByMap(params);
        task.enqueue(new Callback<GetWithParamsResult>() {

            @Override
            public void onResponse(Call<GetWithParamsResult> call, Response<GetWithParamsResult> response) {
                Log.d(TAG, "code: "+response.code());
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<GetWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t);
            }
        });
    }

    public void postWithParams(View view) {
        Call<PostWithParamsResult> task = api.postWithParams("我是关键字");
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                Log.d(TAG, "code: "+response.code());
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t);
            }
        });
    }

    public void postWithUrl(View view) {
        String url = "/post/string?string=aaaaa";
        Call<PostWithParamsResult> task = api.postWithUrl(url);
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                Log.d(TAG, "code: "+response.code());
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t);
            }
        });
    }

    public void postWithBody(View view) {
        CommandItem commandItem = new CommandItem("1","123");
        Call<PostWithParamsResult> task = api.postWithBody(commandItem);
        task.enqueue(new Callback<PostWithParamsResult>() {
            @Override
            public void onResponse(Call<PostWithParamsResult> call, Response<PostWithParamsResult> response) {
                Log.d(TAG, "code: "+response.code());
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<PostWithParamsResult> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t);
            }
        });
    }

    public void postFile(View view) {
        File file = new File("/storage/emulated/0/Download/a.jpg");
        RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file","",body);
        Call<PostFileResult> task = api.postFile(part);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                Log.d(TAG, "code: "+response.code());
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t);
            }

        });
    }
}
