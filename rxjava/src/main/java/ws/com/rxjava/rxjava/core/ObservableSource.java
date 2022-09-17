package ws.com.rxjava.rxjava.core;

public interface ObservableSource<T> {
    void subscribe(Observer observer);
}
