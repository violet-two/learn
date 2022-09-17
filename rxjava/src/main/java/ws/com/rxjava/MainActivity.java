package ws.com.rxjava;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String PATH = "https://tse1-mm.cn.bing.net/th/id/OIP-C.B6pZ8N_dG3MNAYppM-zX0AHaEo?pid=ImgDet&rs=1";

    //弹出加载框
    private ProgressDialog progressDialog;

    private String RX;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
    }

    public void showImageAction(View view) {


        /*
         *TODO @RX思维
         * 启动和终点
         */

        //起点
        // TODO 第二步
        Observable.just(PATH)
                // TODO 第三步
                //需要：001，图片下载需求
                .map(path -> {
                    try {
                        URL url = new URL(path);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(5000);
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == httpURLConnection.HTTP_OK) {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            return bitmap;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .map(bitmap -> {
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    paint.setTextSize(20);
                    Bitmap bitmap1 = drawTextToBitMap(bitmap,"我是su",paint,88,88);
                    return bitmap1;
                })
                .map(bitmap -> {
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    paint.setTextSize(20);
                    Bitmap bitmap1 = drawTextToBitMap(bitmap,"我是su",paint,88,88);
                    return 1;
                })
                //给上面分配异步线程
                .subscribeOn(Schedulers.io())
                //切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
                //关联：观察者设计模式 关联起点和终点 == 订阅
                .subscribe(
                        //终点
                        new Observer<Integer>() {
                            // TODO 第一步
                            //订阅成功
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setTitle("正在加载");
                                progressDialog.show();
                            }

                            // TODO 第四步
                            //上一层给我的响应
                            @Override
                            public void onNext(@NonNull Integer s) {
//                                image.setImageBitmap(s);
                            }

                            //链条发生异常
                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            // TODO 第五步  整个链条思维结束
                            //整个链条全部结束
                            @Override
                            public void onComplete() {
                                progressDialog.dismiss();
                            }
                        });

    }

    private  final Bitmap drawTextToBitMap(Bitmap bitmap, String text, Paint paint,int paddingLeft,int paddingTop){
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true);
        paint.setFilterBitmap(true);
        if(bitmapConfig==null){
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig,true);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text,paddingLeft,paddingTop,paint);
        return bitmap;
    }

    public void action(View view) {
        String[] strings = {"AAAA","BBBB","CCCC"};
        Observable.fromArray(strings)
                .subscribe(s -> Log.d(TAG, "accept: "+s));
    }
}