package com.example.network;

import static java.net.HttpURLConnection.HTTP_OK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.network.adapter.GetResultListAdapter;
import com.example.network.adapter.JsonResultListAdapter;
import com.example.network.api.API;
import com.example.network.domain.JsonResult;
import com.example.network.utils.RetrofitManager;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        rv_result_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom  = 5;
            }
        });
        adapter = new JsonResultListAdapter();
        rv_result_list.setAdapter(adapter);
    }

    public void getRequest(View view) {
        API api = RetrofitManager.getRetrofit().create(API.class);
        Call<JsonResult> call = api.getJson();
        call.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if(response.code()==HTTP_OK){
                    JsonResult result = response.body();
                    updateList(result);
                }
            }
            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }
        });
    }

    private void updateList(JsonResult jsonResult) {
        adapter.setData(jsonResult);
    }
}