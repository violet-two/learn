package ws.com.rxjava.rxjava.core.application;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * author su
 * Create by on 2022/9/16 15:48
 */

/**
 * 手写RxLifecycle
 * @param <T>
 */
public class RxLifecycle<T> implements LifecycleObserver, ObservableTransformer<T,T> {

    final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(){
        Log.d("TAG", "onDestroy: ");
        if(compositeDisposable.isDisposed()){
            compositeDisposable.isDisposed();
        }
    }

    @Override
    public @NonNull ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.doOnSubscribe(disposable -> compositeDisposable.add(disposable));
    }

    public static <T> RxLifecycle<T> bindRxLifecycle(LifecycleOwner lifecycleOwner){
        RxLifecycle<T> rxLifecycle = new RxLifecycle<>();
        lifecycleOwner.getLifecycle().addObserver(rxLifecycle);
        return rxLifecycle;
    }
}
