package com.example.ui_control.customView;

import static java.lang.Math.PI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.ui_control.R;

import java.util.Timer;
import java.util.TimerTask;

import kotlinx.coroutines.Job;

public class CustomDrawView extends View implements LifecycleObserver {
    private float mAngle = 10f;
    private float mWidth = 0f;
    private float mHeight = 0f;
    private float mRadius = 0f;
    //定义直线画笔
    private Paint solidLinePaint = new Paint();
    //定义文本画笔
    private Paint textPaint = new Paint();
    //定义虚线画笔
    private Paint dashedLinePaint = new Paint();
    //定义矢量线画笔
    private Paint vectorLinePaint = new Paint();
    //定义投影画笔
    private Paint fillCyclePaint = new Paint();

    private Path sineWaveSamplesPath = new Path();

    public CustomDrawView(Context context) {
        super(context);
    }

    public CustomDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        solidLinePaint.setStyle(Paint.Style.STROKE);
        solidLinePaint.setStrokeWidth(5f);
        solidLinePaint.setColor(ContextCompat.getColor(context, R.color.white));
        //设置直线画笔属性
        textPaint.setTextSize(50f);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setColor(ContextCompat.getColor(context, R.color.white));
        //设置虚线画笔属性
        dashedLinePaint.setStyle(Paint.Style.STROKE);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10f, 10f}, 0f);
        dashedLinePaint.setPathEffect(dashPathEffect);
        dashedLinePaint.setColor(ContextCompat.getColor(context, R.color.yellow));
        solidLinePaint.setStrokeWidth(5f);
        //设置矢量画笔画笔属性
        vectorLinePaint.setStyle(Paint.Style.STROKE);
        vectorLinePaint.setStrokeWidth(5f);
        vectorLinePaint.setColor(ContextCompat.getColor(context, R.color.green));
        //设置投影画笔属性
        fillCyclePaint.setStyle(Paint.Style.FILL);
        fillCyclePaint.setColor(ContextCompat.getColor(context, R.color.white));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = w < h / 2 ? w / 2 : h / 4;
        mRadius -= 20f;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxises(canvas);
        drawLabel(canvas);
        drawDashedCircle(canvas);
        drawVector(canvas);
        drawProjections(canvas);
        drawSineWave(canvas);
    }

    //绘制轴
    private void drawAxises(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint);
        canvas.drawLine(0f, -mHeight / 2, 0f, mHeight / 2, solidLinePaint);
        canvas.restore();
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 4 * 3);
        canvas.drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, solidLinePaint);
        canvas.restore();
    }

    //绘制文本
    private void drawLabel(Canvas canvas) {
        canvas.drawRect(100f, 100f, 600f, 250f, solidLinePaint);
        canvas.drawText("三角函数与旋转矢量", 120f, 195f, textPaint);
    }

    //绘制圆形
    private void drawDashedCircle(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 4 * 3);
        canvas.drawCircle(0f, 0f, mRadius, dashedLinePaint);
        canvas.restore();
    }

    //绘制投影
    private void drawProjections(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle((mRadius * (float) Math.cos(toRadians(mAngle))), 0f, 20f, fillCyclePaint);
        canvas.restore();

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 4 * 3);
        canvas.drawCircle((mRadius * (float) Math.cos(toRadians(mAngle))), 0f, 20f, fillCyclePaint);
        canvas.restore();

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 4 * 3);
        float x = mRadius * (float) Math.cos(toRadians(mAngle));
        float y = mRadius * (float) Math.sin(toRadians(mAngle));
        canvas.translate(x, -y);
        canvas.drawLine(0f, 0f, 0f, y, solidLinePaint);
        canvas.drawLine(0f, 0f, 0f, -mHeight / 4 + y, dashedLinePaint);
        canvas.restore();
    }

    //绘制余弦函数
    private void drawSineWave(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        int samplesCount = 50;
        float dy = mHeight/2/samplesCount;
        sineWaveSamplesPath.reset();
        sineWaveSamplesPath.moveTo((float) (mRadius*Math.cos(toRadians(mAngle))),0f);
        for (int i = 0; i < samplesCount; i++) {
            float x = (float) (mRadius*Math.cos(i*-0.15+toRadians(mAngle)));
            float y = -dy * i;
            sineWaveSamplesPath.quadTo(x,y,x,y);
        }
        canvas.drawPath(sineWaveSamplesPath,vectorLinePaint);
        canvas.drawTextOnPath("hello world!",sineWaveSamplesPath,100f,0f,textPaint);
    }

    //绘制斜角
    private void drawVector(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 4 * 3);
        canvas.rotate(-mAngle);
        canvas.drawLine(0f, 0f, mRadius, 0f, vectorLinePaint);
        canvas.restore();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void startRotating() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mAngle += 5f;
                        //重新绘图
                        invalidate();
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 0, 100);
            }
        }).start();
    }

    public float toRadians(float radians) {
        return (float) (radians / 180 * PI);
    }
}