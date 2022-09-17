package ws.com.rxjava.rxjava.core.application;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author su
 * Create by on 2022/9/16 15:43
 */
public class SchedulerTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public @NonNull ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());
    }
}
