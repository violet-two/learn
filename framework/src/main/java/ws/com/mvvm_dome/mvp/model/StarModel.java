package ws.com.mvvm_dome.mvp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ws.com.mvvm_dome.mvp.Star;

public class StarModel implements IBaseModel{

    @Override
    public void getRetrofit(IBaseRetCallback iBaseRetCallback) {
        iBaseRetCallback.onSucceed(getData());
        iBaseRetCallback.onFailed("请求失败");
    }

    private List<Star> getData() {
        List<Star> data = new ArrayList<>();
        Star hui = new Star("hui");
        data.add(hui);
        Star yll = new Star("yll");
        data.add(yll);
        Star xj = new Star("xj");
        data.add(xj);
        return data;
    }
}
