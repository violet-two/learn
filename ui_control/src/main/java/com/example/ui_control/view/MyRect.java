package com.example.ui_control.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.ui_control.R;

public class MyRect extends View {
    public MyRect(Context context) {
        super(context);
    }

    public MyRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int color = ta.getColor(R.styleable.MyView_rect_color,0xffff00ff);
        setBackgroundColor(color);
        ta.recycle();
    }
}
