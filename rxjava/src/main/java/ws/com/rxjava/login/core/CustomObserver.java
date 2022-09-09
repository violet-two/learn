package ws.com.rxjava.login.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import ws.com.rxjava.login.bean.ResponseBean;
import ws.com.rxjava.login.bean.SuccessBean;

public abstract class CustomObserver implements Observer<ResponseBean> {
    public abstract void success(SuccessBean successBean);
    public abstract void error(String msg);
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull ResponseBean responseBean) {
        if(responseBean.getData()==null){
            error(responseBean.getMessage()+"请求失败");
        }else{
            success(responseBean.getData());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        error(e.getMessage()+"请检查数据");
    }

    @Override
    public void onComplete() {

    }
}
