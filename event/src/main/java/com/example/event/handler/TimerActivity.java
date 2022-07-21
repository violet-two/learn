package com.example.event.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.example.event.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private ImageView iv_show;
    static class MyHandler extends Handler{

        private final WeakReference<TimerActivity> activity;

        public MyHandler(WeakReference<TimerActivity> activity) {
            this.activity = activity;
        }

        private int[] imageIds = new int[]{R.drawable.iphone,R.drawable.oppo,R.drawable.rongyao,
                R.drawable.vivo,R.drawable.xiaomi};
        private int currenImageId = 0;

        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==0x1){
                activity.get().iv_show.setImageResource(imageIds[currenImageId++%imageIds.length]);
            }
        }
    }

    MyHandler myHandler = new MyHandler(new WeakReference<>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        iv_show = findViewById(R.id.iv_show);

        //定时器
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0x1);
            }
        },0,1000);

    }
}