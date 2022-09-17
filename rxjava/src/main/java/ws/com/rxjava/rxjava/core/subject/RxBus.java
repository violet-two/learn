package ws.com.rxjava.rxjava.core.subject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * author su
 * Create by on 2022/9/17 11:25
 */
public class RxBus {
    private final Subject<Object> mSubject;

    private static RxBus mRxBus;

    public static RxBus getInstance() {
        if (mRxBus == null) {
            mRxBus = new RxBus();
            synchronized (RxBus.class){
                if(mRxBus==null){
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }

    public RxBus() {
        //除了onSubscribe，onNext，onError,onComplete
        //支持线程安全
        mSubject = PublishSubject.create().toSerialized();
    }

    public void post(Object even){
        mSubject.onNext(even);
    }

    public <T>Observable<T> tObservable(Class<T> tClass){
        return mRxBus.mSubject.ofType(tClass);
    }

}
