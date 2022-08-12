package com.example.ui_control.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MySurfaceView extends View {
    private float centerX = 0f;
    private float centerY = 0f;
    private int[] colorList = new int[]{Color.RED,Color.GREEN,Color.YELLOW,Color.BLUE,Color.BLACK, Color.GRAY};
    private Paint paint = new Paint();
    public MySurfaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 2000; i++) {
            paint.setColor(colorList[(int) (Math.random()*colorList.length)]);
            canvas.drawCircle(centerX,centerY,(float) i/5,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        centerX = event.getX();
        centerY = event.getY();
        invalidate();
        return true;

    }
}
