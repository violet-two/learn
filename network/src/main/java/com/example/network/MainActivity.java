package com.example.network;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.network.adapter.GetResultListAdapter;
import com.example.network.domain.GetTextItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "shen";
    private GetResultListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        mAdapter = new GetResultListAdapter();
        rv_list.setAdapter(mAdapter);
    }


    public void loadJson(View view) {
        new Thread(new Thread() {
            @Override
            public void run() {
                try {
//                   URL url = new URL("http://www.sunofbeaches.com/shop/dpi/discovery/categories");
                    URL url = new URL("http://10.0.2.2:9102/get/text");
//                   URL url = new URL("http://128.0.10.30:9102/get/text");
                    //http://119.96.82.181:8081/WS_Administration/regist?regname=s&phone=s&deptId=s&sex=&age=&department=&wsid=&upload=&password=&address=
                    //http://119.96.82.181:8081/WS_Administration/regist?regname=s&phone=s&deptId=s&sex=&age=&department=&wsid=&upload=&password=&address=
                   // http://119.96.82.181:8081/WS_Administration/regist?regname=s&phone=s&deptId=s&sex=&age=&department=&wsid=&upload=&password=&address=
//                    URL url = new URL(" http://119.96.82.181:8081/WS_Administration/regist?regname=s&phone=s&deptId=s&sex=&age=&department=&wsid=&upload=&password=&address=");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(1000);
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String json = bufferedReader.readLine();
                        Log.d(TAG, "json: " + json);
                        Gson gson = new Gson();
                        GetTextItem getTextItem = gson.fromJson(json, GetTextItem.class);
                        updateUI(getTextItem);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateUI(GetTextItem getTextItem) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setData(getTextItem);
            }
        });
    }
}