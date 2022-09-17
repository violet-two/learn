package ws.com.rxjava.rxjava.core;

import ws.com.rxjava.rxjava.core.scheduler.Scheduler;

/**
 * author su
 * Create by on 2022/9/16 11:06
 */
public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T, T> {
    final Scheduler scheduler;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(new SubscribeTask(new SubscribeOnObserver<>(observer)));
    }

    class SubscribeOnObserver<T> implements Observer<T> {
        final Observer<T> downStream;

        SubscribeOnObserver(Observer<T> downStream) {
            this.downStream = downStream;
        }


        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            downStream.onNext(t);
        }

        @Override
        public void onCompete() {
            downStream.onCompete();
        }

        @Override
        public void onError(Throwable throwable) {
            downStream.onError(throwable);
        }
    }

    final class SubscribeTask implements Runnable {
        final SubscribeOnObserver<T> parent;

        SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            source.subscribe(parent);
        }
    }
}
