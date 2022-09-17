package ws.com.rxjava.rxjava.core;

/**
 * author su
 * Create by on 2022/9/15 16:53
 */
public abstract class AbstractObservableWithUpStream<T,U> extends Observable<U>{
    protected final ObservableSource<T> source;

    public AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
