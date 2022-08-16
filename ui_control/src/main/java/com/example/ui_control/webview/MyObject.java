package com.example.ui_control.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.ui_control.R;

public class MyObject {
    private Context mContext;

    public MyObject(Context mContext) {
        this.mContext = mContext;
    }
    @JavascriptInterface
    public void showToast(String name){
        Toast.makeText(mContext,name+"您好！",Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showList(){
        new AlertDialog.Builder(mContext)
                .setTitle("图书列表")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(new String[]{"a","b","c","d"},null)
                .setPositiveButton("确定",null)
                .create()
                .show();
    }
}
