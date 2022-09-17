package ws.com.rxjava.rxjava.core;

import io.reactivex.rxjava3.annotations.NonNull;
import ws.com.rxjava.rxjava.core.scheduler.Scheduler;

/**
 * 被观察者核心抽象类,也是使用框架的入库
 *author su
 *Create by on 2022/9/15 11:04
 */
public abstract class Observable<T> implements ObservableSource<T> {

    @Override
    public void subscribe(Observer observer){
        //和谁建立订阅？
        //怎么建立订阅？
        //为了保证拓展性，交给具体的开发人员实现。这里只提供抽象方法
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public static <T> Observable<T> create(ObservableInSubscribe<T> source){
        return new ObservableCreate<>(source);
    }
    public <@NonNull U> Observable<U> map(Function<T, U> function){
        return new ObservableMap<>(this, function);
    }
    public <@NonNull U> Observable<U> flatMap(Function<T, ObservableSource<U>> function){
        return new ObservableFlatMap<>(this, function);
    }
    public ObservableSubscribeOn<T> subscribeOn(Scheduler scheduler){
        return new ObservableSubscribeOn<>(this,scheduler);
    }
    public ObservableObserveOn<T> observeOn(Scheduler scheduler){
        return new ObservableObserveOn<>(this,scheduler);
    }

}
