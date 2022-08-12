package com.example.ui_control.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.ui_control.bean.Bubble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BubbleSurfaceView extends SurfaceView {
    private float centerX = 0f;
    private float centerY = 0f;
    private int[] colorList = new int[]{Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.BLACK, Color.GRAY};
    private Paint paint = new Paint();
    private List<Bubble> bubbleList = new ArrayList<>();


    public BubbleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(30f, 20f);
        paint.setPathEffect(discretePathEffect);
        init();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int color = colorList[(int) (Math.random() * colorList.length)];
        Bubble bubble = new Bubble(x, y, color, 1f);
        bubbleList.add(bubble);
        if (bubbleList.size() > 30) bubbleList.remove(0);
        return super.onTouchEvent(event);
    }

    public void init() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                while (true) {
                    if (getHolder().getSurface().isValid()) {
                        Canvas canvas = getHolder().lockCanvas();
                        canvas.drawColor(Color.BLACK);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                           List<Bubble> bubbleList2 =bubbleList;
                            bubbleList.stream().filter(bubble -> {
                                if (bubble.getRadius() < 3000)
                                    return true;
                                return false;
                            }).forEach(bubble -> {
                                paint.setColor(bubble.getColor());
                                float radius = bubble.getRadius();
                                canvas.drawCircle(bubble.getX(), bubble.getY(), radius, paint);
                                radius += 10f;
                                bubble.setRadius(radius);
                            });
                        }
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }, 0, 100);
    }

}
