package ws.com.rxjava.rxjava.core;

/**
 * author su
 * Create by on 2022/9/15 11:09
 */
public interface ObservableInSubscribe<T> {
    void subscribe(Emitter<T> emitter);
}
