package ws.com.rxjava.rxjava.core;

public interface Observer<T> {

    void onSubscribe();

    void onNext(T t);

    void onCompete();

    void onError(Throwable throwable);
}
