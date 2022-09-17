package ws.com.rxjava.rxjava.core.scheduler;

/**
 * author su
 * Create by on 2022/9/16 11:07
 */
public abstract class Scheduler {
    public abstract Worker createWorker();

    public interface Worker{
        void schedule(Runnable runnable);
    }
}
