package ws.com.rxjava.rxjava.core.application;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ws.com.rxjava.R;
import ws.com.rxjava.rxjava.core.Function;
import ws.com.rxjava.rxjava.core.Observable;
import ws.com.rxjava.rxjava.core.Observer;
import ws.com.rxjava.rxjava.core.scheduler.Schedules;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        Observable.create(emitter -> {
                    Log.d(TAG, "Thread: " + Thread.currentThread());
                    String a = "aaa";
                    emitter.onNext(a);
//            emitter.onError(new Exception());
                    emitter.onCompete();
                })
                .subscribeOn(Schedules.newThread())
                .observeOn(Schedules.mainThread())
                .map(o -> {
                    Log.d(TAG, "Thread: map" + Thread.currentThread());
                    return 1;
                })
//                .flatMap(o -> Observable.create(emitter -> emitter.onNext(1)))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe() {
                        Log.d(TAG, "Thread: onSubscribe" + Thread.currentThread());
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.d(TAG, "Thread: onNext" + Thread.currentThread());
                        Log.d(TAG, "onNext: " + o);
                    }

                    @Override
                    public void onCompete() {
                        Log.d(TAG, "Thread: onCompete" + Thread.currentThread());
                        Log.d(TAG, "onCompete: ");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d(TAG, "Thread: onError" + Thread.currentThread());
                        Log.d(TAG, "onError: " + throwable);
                    }
                });
    }
}