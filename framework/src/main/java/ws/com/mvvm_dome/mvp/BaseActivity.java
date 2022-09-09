package ws.com.mvvm_dome.mvp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ws.com.mvvm_dome.mvp.annotation.InjectPresenter;
import ws.com.mvvm_dome.mvp.presenter.BasePresenter;
import ws.com.mvvm_dome.mvp.view.IBaseView;

public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity implements IBaseView {
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.onAttach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

//    List<BasePresenter> presenters;
////    private IMvpProxy mvpProxy;
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        presenters = new ArrayList<>();
////        mvpProxy = new MvpProxyImpl(this);
////        mvpProxy.bindAndPresenter();
//        //通过注解的方式生成我们的Presenter
//        Field[] fields = this.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            InjectPresenter[] injectPresenter = field.getAnnotationsByType(InjectPresenter.class);
//            Class<? extends BasePresenter> presenterClazz = null;
//            if (injectPresenter != null) {
//                try {
//                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                try {
//                    BasePresenter basePresenter = presenterClazz.newInstance();
//                    basePresenter.onAttach(this);
//
//                    field.setAccessible(true);
//                    field.set(this, basePresenter);
//                    presenters.add(basePresenter);
//                    basePresenter.onAttach(this);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (presenters == null) {
//            return;
//        }
//        for (BasePresenter basePresenter : presenters) {
//            basePresenter.onDetach();
//        }
////        mvpProxy.unbindAndPresenter();
//    }

    protected abstract T createPresenter();
}
