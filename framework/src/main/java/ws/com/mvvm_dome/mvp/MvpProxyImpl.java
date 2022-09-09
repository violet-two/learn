package ws.com.mvvm_dome.mvp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ws.com.mvvm_dome.mvp.annotation.InjectPresenter;
import ws.com.mvvm_dome.mvp.presenter.BasePresenter;
import ws.com.mvvm_dome.mvp.view.IBaseView;

public class MvpProxyImpl<V extends IBaseView> implements IMvpProxy {
    private V mView;
    List<BasePresenter> presenters;

    public MvpProxyImpl(V mView) {
        this.mView = mView;
        presenters = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindAndPresenter() {
        //通过注解的方式生成我们的Presenter
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter[] injectPresenter = field.getAnnotationsByType(InjectPresenter.class);
            Class<? extends BasePresenter> presenterClazz = null;
            if (injectPresenter != null) {
                try {
                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    BasePresenter basePresenter = presenterClazz.newInstance();
                    basePresenter.onAttach(mView);

                    field.setAccessible(true);
                    field.set(mView, basePresenter);
                    presenters.add(basePresenter);
                    basePresenter.onAttach(mView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unbindAndPresenter() {
        if (presenters == null) {
            return;
        }
        for (BasePresenter basePresenter : presenters) {
            basePresenter.onDetach();
        }
    }
}
