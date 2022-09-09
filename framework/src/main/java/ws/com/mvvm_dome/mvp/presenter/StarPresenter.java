package ws.com.mvvm_dome.mvp.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import ws.com.mvvm_dome.mvp.Star;
import ws.com.mvvm_dome.mvp.model.IBaseModel;
import ws.com.mvvm_dome.mvp.model.StarModel;
import ws.com.mvvm_dome.mvp.view.IStarView;

public class StarPresenter extends BasePresenter<IStarView, StarModel> {
    public void fetch() {
        this.mModel.getRetrofit(new IBaseModel.IBaseRetCallback() {
            @Override
            public void onSucceed(List<Star> starList) {
                mView.get().showStarList(starList);
            }

            @Override
            public void onFailed(String t) {
                mView.get().showErrorMessage(t);
            }
        });
    }
}
