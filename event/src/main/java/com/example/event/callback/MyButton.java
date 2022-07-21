package com.example.event.callback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {

    public MyButton(Context context, AttributeSet set) {
        super(context,set);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.v("ws", "MyButtonActivity in myButton");
        return false;
    }
}