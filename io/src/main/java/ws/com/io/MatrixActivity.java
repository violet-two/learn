package ws.com.io;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MatrixActivity extends AppCompatActivity {

    private GestureDetector detector;
    private float currentScale;
    private Matrix matrix;
    private ImageView imageView;
    private Bitmap bitmap;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float vx = velocityX > 4000 ? -4000f : velocityX;
                vx = velocityX < -4000 ? -4000f : velocityX;
                //根据手势的速度来计算缩放比例，如果vx>0,则放大图片，否则就缩小
                currentScale += currentScale * vx / 4000.0f;
                //保证不会小于0
                currentScale = currentScale > 0.01 ? currentScale : 0.01f;
                //重置matrix
                matrix.reset();
                //缩放Matrix
                matrix.setScale(currentScale, currentScale, 160f, 200f);
                BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();
                //回收图片
                if (!tmp.getBitmap().isRecycled()) {
                    tmp.getBitmap().recycle();
                }
                //根据原始图位和matrix创建心图片
                /*
                x int 子位图第一个像素在源位图的X坐标
                y int    子位图第一个像素在源位图的y坐标
                width int     子位图每一行的像素个数
                height int    子位图的行数
                m  Matrix     对像素值进行变换的可选矩阵
                filter boolean    如果为true，源图要被过滤。该参数仅在matrix包含了超过一个翻转才有效
                Returns Bitmap    一个描述了源图指定子集的位图
                */
                Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                imageView.setImageBitmap(bitmap2);
                return true;
            }
        });

        imageView = findViewById(R.id.show);
        matrix = new Matrix();
        //获取源图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        //获得为图宽
        width = bitmap.getWidth();
        //获得位图高
        height = bitmap.getHeight();
        //设置imageView初始化时显示的图片
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.a));
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return detector.onTouchEvent(me);
    }
}