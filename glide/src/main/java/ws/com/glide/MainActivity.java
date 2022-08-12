package ws.com.glide;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;

import java.io.File;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ws.com.glide.lifecycle.MyLifecycleFragment;
import ws.com.glide.options.GlideApp;


public class MainActivity extends AppCompatActivity {

    private ImageView ivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPic = findViewById(R.id.iv_pic);

//        RequestOptions requestOptions = new RequestOptions().centerCrop();

//        Glide.with(this)
//                .load("https://i.bmp.ovh/imgs/2022/08/09/98d0ba5af1313180.png")
//                .into(ivPic);


        GlideApp.with(this)
//                .asFile()
                .fade()
                .load("https://i.bmp.ovh/imgs/2022/08/09/98d0ba5af1313180.png")
                .applyAvatar(144*3)
//                .listener(new RequestListener<File>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
                .into(ivPic);

//        FutureTarget<Drawable> target = GlideApp.with(this)
////                .asFile()
//                .asDrawable()
//                .load("https://i.bmp.ovh/imgs/2022/08/09/98d0ba5af1313180.png")
//                .applyAvatar(144 * 3)
//                .submit();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Drawable drawable = target.get();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ivPic.setImageDrawable(drawable);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
        testLinkedHashMap();
    }


    private void testLinkedHashMap(){
        Map<String,String> map = new LinkedHashMap<>(0,0.75f,false);
        map.put("A","A");
        map.put("B","B");
        map.put("C","C");
        map.put("D","D");
        map.put("E","E");
        map.put("F","F");
        map.get("A");
        map.put("N","N");
        for(Map.Entry<String,String> entry : map.entrySet()){
            Log.i("TAG", "testLinkHashMap: "+entry.getKey()+":"+entry.getValue());
        }
//        MyLifecycleFragment myLifecycleFragment = new MyLifecycleFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction()
    }



}