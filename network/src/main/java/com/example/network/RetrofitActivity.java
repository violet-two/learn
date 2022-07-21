package com.example.network;

import static java.net.HttpURLConnection.HTTP_OK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.network.adapter.GetResultListAdapter;
import com.example.network.adapter.JsonResultListAdapter;
import com.example.network.api.API;
import com.example.network.domain.JsonResult;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    private RecyclerView rv_result_list;
    private JsonResultListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();
    }

    private void initView() {
        rv_result_list = findViewById(R.id.rv_result_list);
        rv_result_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JsonResultListAdapter();
        rv_result_list.setAdapter(adapter);
    }

    public void getRequest(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9102")
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getJson();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.code()==HTTP_OK){
                    try {
                        Log.d(TAG, "json: "+response.body().string());
                        String result = response.body().string();
                        Log.d(TAG, "result: "+result);
                        Gson gson = new Gson();
                        JsonResult jsonResult = gson.fromJson(result, JsonResult.class);
                        updateList(jsonResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }
        });
    }

    private void updateList(JsonResult jsonResult) {
        adapter.setData(jsonResult);
    }
}