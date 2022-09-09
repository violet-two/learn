package ws.com.mvvm_dome.mvp.model;

import java.util.List;

import ws.com.mvvm_dome.mvp.Star;

public interface IBaseModel {

    //进行模拟网络请求
    void getRetrofit(IBaseRetCallback iBaseRetCallback);
    interface IBaseRetCallback{
        void onSucceed(List<Star> starList);
        void onFailed(String t);
    }
}
