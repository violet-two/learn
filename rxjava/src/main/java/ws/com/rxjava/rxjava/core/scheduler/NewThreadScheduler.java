package ws.com.rxjava.rxjava.core.scheduler;

import java.util.concurrent.ExecutorService;

/**
 * author su
 * Create by on 2022/9/16 11:33
 */
public class NewThreadScheduler extends Scheduler{
    final ExecutorService executorService;

    public NewThreadScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Worker createWorker() {
        return new NewThreadWorker(executorService);
    }

    static final class NewThreadWorker implements Worker{
        final ExecutorService mapper;

        NewThreadWorker(ExecutorService mapper) {
            this.mapper = mapper;
        }

        @Override
        public void schedule(Runnable runnable) {
            mapper.execute(runnable);
        }
    }
}
