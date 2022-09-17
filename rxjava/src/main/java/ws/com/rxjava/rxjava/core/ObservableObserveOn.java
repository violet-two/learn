package ws.com.rxjava.rxjava.core;

import java.util.ArrayDeque;
import java.util.Deque;

import ws.com.rxjava.rxjava.core.scheduler.Scheduler;

/**
 * author su
 * Create by on 2022/9/16 11:40
 */
public class ObservableObserveOn<T> extends AbstractObservableWithUpStream<T,T> {
    final Scheduler scheduler;
    public ObservableObserveOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        Scheduler.Worker worker = scheduler.createWorker();
        source.subscribe(new ObserveOnObserver(observer,worker));
    }

    static final class ObserveOnObserver<T> implements Observer<T>,Runnable{
        final Observer<T> downStream;
        final Scheduler.Worker worker;
        final Deque<T> queue;


        volatile  boolean done;
        volatile  Throwable error;
        volatile boolean over;
        ObserveOnObserver(Observer<T> downStream, Scheduler.Worker worker) {
            this.downStream = downStream;
            this.worker = worker;
            queue = new ArrayDeque<>();
        }


        @Override
        public void run() {
            downStream.onSubscribe();
            drainNormal();
        }
        //从队列中排放事件并处理
        private void drainNormal() {
            final Deque<T> q = queue;
            final Observer<T> a = downStream;
            while(true){
                boolean d = done;

                T t  = q.poll();//取出数据，不会报错
                boolean empty = t == null;
                if(checkTerminated(d,empty,a)){
                    return;
                }
                if (empty) {
                    break;
                }
                a.onNext(t);
            }
        }

        private boolean checkTerminated(boolean d, boolean empty, Observer<T> a) {
            if(over){
                queue.clear();
                return true;
            }
            if(d){
                Throwable e = error;
                if(e!=null){
                    over = true;
                    a.onError(error);
                    return true;
                }else if (empty){
                    over = true;
                    a.onCompete();
                    return true;
                }
            }
            return false;
        }

        @Override
        public void onSubscribe() {

        }

        @Override
        public void onNext(T t) {
            queue.offer(t);
            schedule();
        }

        private void schedule() {
            worker.schedule(this);
        }

        @Override
        public void onCompete() {
        }

        @Override
        public void onError(Throwable throwable) {
        }
    }
}
