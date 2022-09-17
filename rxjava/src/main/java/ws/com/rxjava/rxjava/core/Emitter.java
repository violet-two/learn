package ws.com.rxjava.rxjava.core;

/**
 * 时间发射器接口
 */
public interface Emitter<T> {
    void onNext(T t);

    void onCompete();

    void onError(Throwable throwable);
}
