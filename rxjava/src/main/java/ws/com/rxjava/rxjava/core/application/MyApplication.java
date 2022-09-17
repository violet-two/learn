package ws.com.rxjava.rxjava.core.application;

import android.app.Application;
import android.util.Log;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/**
 * author su
 * Create by on 2022/9/16 15:35
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> Log.d("TAG", "accept: "+throwable.toString()));
    }
}
