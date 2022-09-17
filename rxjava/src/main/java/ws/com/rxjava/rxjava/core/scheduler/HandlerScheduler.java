package ws.com.rxjava.rxjava.core.scheduler;


import android.os.Handler;

/**
 * author su
 * Create by on 2022/9/16 11:09
 */
public class HandlerScheduler extends Scheduler{
    final Handler handler;

    public HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWork(handler);
    }

    static final class HandlerWork implements Worker{

        final Handler mapper;

        HandlerWork(Handler mapper) {
            this.mapper = mapper;
        }

        @Override
        public void schedule(Runnable runnable) {
            mapper.post(runnable);
        }
    }
}
