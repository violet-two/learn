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

public class MatrixActivity extends AppCompatActivity{

    private GestureDetector detector;
    private float currentScale;
    private Matrix matrix;
    private ImageView imageView;
    private Bitmap bitmap;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float vx = velocityX>4000?-4000f:velocityX;
                vx = velocityX<-4000?-4000f:velocityX;

                currentScale += currentScale *vx/4000.0f;
                currentScale = currentScale>0.01?currentScale:0.01f;

                matrix.reset();

                matrix.setScale(currentScale,currentScale,160f,200f);
                BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();

                if(!tmp.getBitmap().isRecycled()){
                    tmp.getBitmap().recycle();
                }

                Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
                imageView.setImageBitmap(bitmap2);

                return true;
            }
        });

        imageView = findViewById(R.id.show);
        matrix = new Matrix();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.a));
    }
    @Override
    public boolean onTouchEvent(MotionEvent me){
        return detector.onTouchEvent(me);
    }
}