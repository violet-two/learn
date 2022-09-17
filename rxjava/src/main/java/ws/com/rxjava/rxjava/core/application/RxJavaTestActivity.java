package ws.com.rxjava.rxjava.core.application;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import ws.com.rxjava.R;
import ws.com.rxjava.rxjava.core.subject.RxBus;

public class RxJavaTestActivity extends AppCompatActivity {

    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);
        textView = findViewById(R.id.textView);

        RxBus.getInstance().post("这是Rxjava发射的事件");


        Observable.create(emitter -> {
                    Log.d("TAG", "onCreate: 开始请求数据");
                    Thread.sleep(5000);
                    Log.d("TAG", "onCreate: 数据请求结束");
                    emitter.onNext("success");
                    emitter.onComplete();
                })
                //复用线程切换操作符
                .compose(new SchedulerTransformer<>())
                //防止内存泄漏
                .compose(RxLifecycle.bindRxLifecycle(this))
                .subscribe(
                        o -> {
                            Log.d("TAG", "onCreate: "+o);
                            textView.setText(o.toString());
                            Log.d("TAG", "onCreate: "+textView.getText().toString());
                        }
                );

    }
}