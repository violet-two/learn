package com.example.network;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PicLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_load);
    }

    public void loadPic(View view) {
        ImageView imageView = findViewById(R.id.iv_result_image);
        int i = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.a,options);
        //拿到图片大小
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        Log.d("ws", "outHeight" + outHeight + ",outWidth" + outWidth);
        int measuredHeight = imageView.getMeasuredHeight();
        int measuredWidth = imageView.getMeasuredWidth();
        Log.d("ws", "measuredHeight=" + measuredHeight + ",measuredWidth=" + measuredWidth);

        options.inSampleSize = 1;
        //分别相除，取小值
        if (outHeight > measuredHeight || outWidth > measuredHeight) {
            int subHeight = outHeight / measuredHeight;
            int subWidth = outWidth / measuredHeight;
            options.inSampleSize = subHeight > subWidth ? subWidth : subHeight;
        }
        options.inJustDecodeBounds = false;

        final Bitmap bitImage = BitmapFactory.decodeResource(getResources(), R.mipmap.a,  options);
        imageView.setImageBitmap(bitImage);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("https://tse1-mm.cn.bing.net/th/id/OIP-C.mb5H7dYDOybJXGGOYO-ViAHaGy?pid=ImgDet&rs=1");
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setConnectTimeout(10000);
//                    connection.setRequestMethod("GET");
//                    connection.connect();;
//                    int responseCode = connection.getResponseCode();
//                    if(responseCode==HttpURLConnection.HTTP_OK){
//                        InputStream inputStream = connection.getInputStream();
//                        //转成bitMap
//                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        //更新UI
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                ImageView imageView = findViewById(R.id.iv_result_image);
//                                imageView.setImageBitmap(bitmap);
//                            }
//                        });
//                    }
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}