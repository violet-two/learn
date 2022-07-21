package com.example.ui_control.progress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;


import com.example.ui_control.R;

import java.lang.ref.WeakReference;

public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar pb_view;
    private ProgressBar pb_bar;
    private ProgressBar pb_bar2;

    private int[] data = new int[100];
    private int hasData = 0;
    //记录Progress的完成度
    int status = 0;
    int pbBarStatus = 0;


//    static class MyHandler extends Handler {
//        private WeakReference<ProgressBarActivity> activity;
//
//        MyHandler(WeakReference<ProgressBarActivity> activity) {
//            this.activity = activity;
//        }
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            if (msg.what == 0x111) {
//                activity.get().pb_bar.setProgress(activity.get().status);
//            }
//        }
//    }

//    MyHandler myHandler = new MyHandler(new WeakReference<>(this));

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        pb_view = findViewById(R.id.pb_view);
        pb_bar = findViewById(R.id.pb_bar);
        pb_bar2 = findViewById(R.id.pb_bar2);

        findViewById(R.id.btn_test).setOnClickListener(this);
        findViewById(R.id.btn_bar).setOnClickListener(this);
        new Thread(() -> {
            while (status < 100) {
                status = doWork();
                pb_bar.setProgress(status);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                if (pb_view.getVisibility() == View.GONE) {
                    pb_view.setVisibility(View.VISIBLE);
                } else {
                    pb_view.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_bar:
                pbBarStatus+=10;
                pb_bar2.setProgress(pbBarStatus);
                break;
        }
    }

    //模拟一个耗时操作
    public int doWork() {
        //为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}