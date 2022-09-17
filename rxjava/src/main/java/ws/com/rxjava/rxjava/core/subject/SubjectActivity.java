package ws.com.rxjava.rxjava.core.subject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Unit;
import ws.com.rxjava.R;
import ws.com.rxjava.rxjava.core.application.RxJavaTestActivity;
import ws.com.rxjava.rxjava.core.application.RxLifecycle;
import ws.com.rxjava.rxjava.core.application.SchedulerTransformer;
import ws.com.rxjava.rxjava.core.retrofit.LoginBean;
import ws.com.rxjava.rxjava.core.retrofit.RetrofitUtils;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

//        startActivity(new Intent(this, RxJavaTestActivity.class));

//        test();

//        RxBus.getInstance().tObservable(String.class)
//                .compose(RxLifecycle.bindRxLifecycle(this))
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Throwable {
//                        Log.d("TAG", "accept:接收到 "+s);
//                    }
//                });

//        applyPermission();

//        TextView textView = findViewById(R.id.textView);
//        RxView.clicks(textView)
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(unit -> Log.d("TAG", "accept: "));

        RetrofitUtils.getApi().getUserInfo()
                .compose(new SchedulerTransformer<>())
                .subscribe(loginBean -> Log.d("TAG", "accept: "+loginBean));
    }

    private void test() {
        //        AsyncSubject<Object> objectAsyncSubject = AsyncSubject.create();
//        BehaviorSubject<Object> objectAsyncSubject = BehaviorSubject.create();
//        ReplaySubject<Object> objectAsyncSubject = ReplaySubject.create();
        PublishSubject<Object> objectAsyncSubject = PublishSubject.create();
        objectAsyncSubject.onNext("A");
        objectAsyncSubject.onNext("B");
        objectAsyncSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                Log.d("TAG", "accept: " + o);
            }
        });
        objectAsyncSubject.onNext("C");
        objectAsyncSubject.onComplete();
    }

    public void applyPermission(){
        new RxPermissions(this).request("android.permission.CAMERA")
                .compose(RxLifecycle.bindRxLifecycle(this))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        if(aBoolean){
                            Log.d("TAG", "accept: 开启了权限");
                        }
                    }
                });

    }
}