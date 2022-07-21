package com.example.event.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.event.R;

import java.util.ArrayList;
import java.util.List;

public class CalPrimeNumberActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String UPPER_NUM = "upper";
    private EditText et_context;
    private CalThread calThread;


    class CalThread extends Thread {
        private Handler mHandler;

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        List<Integer> nums = new ArrayList<Integer>();
                        outer:
                        for (int i = 2; i <= upper; i++) {
                            int j = 2;
                            while (j <= Math.sqrt(i)) {
                                if (i != 2 && i % j == 0) {
                                    continue outer;
                                }
                                j++;
                            }
                            nums.add(i);
                        }
                        Toast.makeText(CalPrimeNumberActivity.this, nums.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_prime_number);
        et_context = findViewById(R.id.et_context);
        findViewById(R.id.btn_cal).setOnClickListener(this);
        calThread = new CalThread();
        calThread.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cal:
                Message msg = new Message();
                msg.what=0x123;
                Bundle bundle = new Bundle();
                try {
                    bundle.putInt(UPPER_NUM,Integer.parseInt(et_context.getText().toString()));
                }catch (Exception e){
                    bundle.putInt(UPPER_NUM,1000);
                }
                msg.setData(bundle);
                calThread.mHandler.sendMessage(msg);
                break;
        }
    }
}