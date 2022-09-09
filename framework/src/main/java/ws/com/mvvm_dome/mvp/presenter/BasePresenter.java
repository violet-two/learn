package ws.com.mvvm_dome.mvp.presenter;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import ws.com.mvvm_dome.mvp.model.IBaseModel;
import ws.com.mvvm_dome.mvp.view.IBaseView;
import ws.com.mvvm_dome.mvp.view.IStarView;

public class BasePresenter<T extends IBaseView, K extends IBaseModel> {
    //使用弱引用，解决内存泄漏问题
    protected WeakReference<IStarView> mView;
    protected K mModel;

    public BasePresenter() {
        //通过反射创建
        Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        try {
            mModel = (K) ((Class) types[1]).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //绑定
    public void onAttach(IBaseView view) {
        this.mView = new WeakReference(view);
    }

    //解绑
    public void onDetach() {
        if (mView == null) {
            return;
        }
        mView.clear();
        mView = null;
    }
}
