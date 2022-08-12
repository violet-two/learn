package com.example.ui_control.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SurfaceView extends android.view.SurfaceView {
    private float centerX = 0f;
    private float centerY = 0f;
    private int[] colorList = new int[]{Color.RED,Color.GREEN,Color.YELLOW,Color.BLUE,Color.BLACK, Color.GRAY};
    private Paint paint = new Paint();
    public SurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        centerX = event.getX();
        centerY = event.getY();
        Canvas canvas = getHolder().lockCanvas();
        //清空画布
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < 2000; i++) {
            paint.setColor(colorList[(int) (Math.random()*colorList.length)]);
            canvas.drawCircle(centerX,centerY,(float) i/5,paint);
        }
        getHolder().unlockCanvasAndPost(canvas);
        return true;
    }
}
