package com.example.ui_control.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.ui_control.R;

import java.util.Objects;

public class EditTextWithClear extends androidx.appcompat.widget.AppCompatEditText {
    private Drawable drawable = null;

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //应用自定义属性，并获取资源
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EditTextWithClear,
                0, 0);
        try {
            int resourceId = a.getResourceId(R.styleable.EditTextWithClear_clearIcon, 0);
            if(resourceId!=0){
                drawable = ContextCompat.getDrawable(getContext(), resourceId);
            }
        } finally {
            a.recycle();
        }
    }

    private CharSequence text;
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        this.text = text;
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        toggleClearIcon();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (drawable != null) {
            if (event.getAction() == MotionEvent.ACTION_UP
                    //Intrinsic固有的
                    //计算点击按钮事件的范围
                    && x > getWidth() - drawable.getIntrinsicWidth()
                    && x < getWidth()
                    && y > getHeight() / 2 - drawable.getIntrinsicHeight() / 2
                    && y < getHeight() / 2 + drawable.getIntrinsicHeight() / 2)
                if (text != null) {
                Objects.requireNonNull(getText()).clear();
                setCompoundDrawables(null, null, null, null);
            }
        }
        return super.onTouchEvent(event);
    }

    //焦点改变事件
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        toggleClearIcon();
    }

    //添加icon
    private void toggleClearIcon() {
        if (isFocused()&&text != null&&text.length()>0) {
            //按照原有比例大小显示图片
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
        } else {
            //手动设置大小
            setCompoundDrawables(null,null,null,null);
        }
    }
}
