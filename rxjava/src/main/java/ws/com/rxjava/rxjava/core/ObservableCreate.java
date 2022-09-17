package ws.com.rxjava.rxjava.core;

/**
 * author su
 * Create by on 2022/9/15 11:16
 */
public class ObservableCreate<T> extends Observable<T>{
    final ObservableInSubscribe<T> source;

    public ObservableCreate(ObservableInSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        CreateEmitter<T> emitter = new CreateEmitter<T>(observer);
        source.subscribe(emitter);
    }



    static class CreateEmitter<T> implements Emitter<T>{
        Observer<T> observer;
        boolean done;
        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if(done) return;
            observer.onNext(t);
        }

        @Override
        public void onCompete() {
            if(done) return;
            observer.onCompete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if(done)  return;
            observer.onError(throwable);
            done = true;
        }
    }

}
