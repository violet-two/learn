package com.example.ui_control.customView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.ui_control.R;


public class FindMeView extends View {
    private final Drawable drawable;
    private Bitmap faceBitMap;
    private float faceX = 0f;
    private float faceY = 0f;
    private Path path = new Path();
    private Paint paint = new Paint();

    public FindMeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        drawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_face_24);
////        faceBitMap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        faceBitMap = Bitmap.createBitmap(300,300,config);
        Canvas canvas = new Canvas(faceBitMap);
        drawable.setBounds(0, 0, 300,300);
        drawable.draw(canvas);

    }

    private void randomPosit() {
        //避免图标突破框架
        faceX = (float) (Math.random() * (getWidth() - 300));
        faceY = (float) (Math.random() * (getHeight() - 300));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawBitmap(faceBitMap, faceX, faceY, null);
        canvas.drawPath(path,paint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            randomPosit();
            path.reset();
            path.addRect(0f,0f,getWidth(),getHeight(),Path.Direction.CW);
            path.addCircle(event.getX(),event.getY(),200f,Path.Direction.CCW);
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.reset();
            path.addRect(0f,0f,getWidth(),getHeight(),Path.Direction.CW);
            path.addCircle(event.getX(),event.getY(),200f,Path.Direction.CCW);
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            path.reset();
        }
        invalidate();
        return true;
    }
}
