package com.example.ui_control.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.ui_control.bean.Bubble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BubbleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static DiscretePathEffect discretePathEffect;
    private float centerX = 0f;
    private float centerY = 0f;
    private int[] colorList = new int[]{Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.BLACK, Color.GRAY};
    private static Paint paint = new Paint();
    private List<Bubble> bubbleList = new ArrayList<>();

    public static void changeDiscretePathEffect(float length, float deviation) {
        discretePathEffect = new DiscretePathEffect(length, deviation);
        paint.setPathEffect(discretePathEffect);
    }

    public BubbleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        changeDiscretePathEffect(0f, 0f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int color = colorList[(int) (Math.random() * colorList.length)];
        Bubble bubble = new Bubble(x, y, color, 1f);
        synchronized (bubbleList) {
            bubbleList.add(bubble);
        }
        if (bubbleList.size() > 30) bubbleList.remove(0);
        init();
        return super.onTouchEvent(event);
    }

    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        while (true) {
                            if (getHolder().getSurface().isValid()) {
                                Canvas canvas = getHolder().lockCanvas();
                                try {
                                    canvas.drawColor(Color.BLACK);
                                } catch (Exception e) {
                                }
                                synchronized (bubbleList) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                           List<Bubble> bubbleList2 =bubbleList;
                                        bubbleList.stream().filter(bubble -> {
                                            if (bubble.getRadius() < 3000)
                                                return true;
                                            return false;
                                        }).forEach(bubble -> {
                                            paint.setColor(bubble.getColor());
                                            float radius = bubble.getRadius();
                                            try {
                                                canvas.drawCircle(bubble.getX(), bubble.getY(), radius, paint);
                                            } catch (Exception e) {
                                            }
                                            radius += 10f;
                                            bubble.setRadius(radius);
                                        });
                                    }
                                }
                                try {
                                    getHolder().unlockCanvasAndPost(canvas);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }, 0, 1000);
            }
        }).start();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
