package com.example.event.callback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.event.R;

public class MyButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_button);
        Button btn_dianji  = findViewById(R.id.btn_dianji);
        btn_dianji.setOnTouchListener((view,event)->{
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                Log.v("ws", "MyButtonActivity in listener");
            }
            return false;
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.v("ws", "MyButtonActivity in activity");
        return false;
    }
}