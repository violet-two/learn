package com.example.ui_control.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.ui_control.R;

public class JsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        WebView show = findViewById(R.id.show);
        //加载网页
        show.loadUrl("file:///android_asset/test.html");
        //设置websetting对象
        WebSettings webSettings = show.getSettings();

        //开启调用
        webSettings.setJavaScriptEnabled(true);
        //这样test.html页面中的javascript可以通过myObj来调用MyObject的方法
        show.addJavascriptInterface(new MyObject(this),"myObj");
    }
}