package com.example.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.network.domain.CommentItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestTestActivity extends AppCompatActivity {

    private static String BASE_URL = "HTTP://10.0.2.2:9102";
    private static String TAG = "RequestTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_test);
    }

    public void postRequest(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream outputStream = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL("http://10.0.2.2:9102/post/comment");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(10000);
                    urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    urlConnection.setRequestProperty("Accept", "application/json,text/plain,*/*");
                    CommentItem commentItem = new CommentItem("1", "哈哈");
                    Gson gson = new Gson();
                    String gsonStr = gson.toJson(commentItem);

                    //连接
                    urlConnection.connect();
                    //把数据给到服务
                    outputStream = urlConnection.getOutputStream();
                    byte[] bytes = gsonStr.getBytes("UTF-8");
                    outputStream.write(bytes);
                    outputStream.flush();
                    //拿结果
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == urlConnection.HTTP_OK) {
                        inputStream = urlConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        Log.d("shen", "run: " + bufferedReader.readLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }


    public void postWithParams(View view) {
        HashMap<String, String> map = new HashMap<>();
        map.put("string", "这是字符串");
        startRequest(map, "POST", "/post/string");
    }

    public void getWithParams(View view) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword", "这是关键字");
        map.put("page", "12");
        map.put("order", "1");
        startRequest(map, "GET", "/get/param");
    }

    private void startRequest(HashMap<String, String> params, String method, String api) {
        new Thread(new Runnable() {
            private BufferedReader bufferedReader;

            @Override
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    //组装参数
                    if (params != null && params.size() > 0) {
                        sb.append("?");
                        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, String> next = iterator.next();
                            sb.append(next.getKey());
                            sb.append("=");
                            sb.append(next.getValue());
                            if (iterator.hasNext()) {
                                sb.append("&");
                            }
                        }
                    }
                    String params = sb.toString();
                    URL url;
                    if (params != null) {
                        url = new URL(BASE_URL + api + params);
                    } else {
                        url = new URL(BASE_URL + api);
                    }
                    Log.d(TAG, "sb: " + url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod(method);
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                    httpURLConnection.setRequestProperty("Accept", "application/json,text/plain,*/*");
                    //拿结果
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == httpURLConnection.HTTP_OK) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        Log.d("shen", "run: " + bufferedReader.readLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}