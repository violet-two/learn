package ws.com.rxjava.login.core;

import io.reactivex.rxjava3.core.Observable;
import ws.com.rxjava.login.bean.ResponseBean;
import ws.com.rxjava.login.bean.SuccessBean;

public class LoginEngine {

    public static Observable<ResponseBean> login(String name,String password){
        ResponseBean responseBean = new ResponseBean();
        if("su".equals(name)&&"123456".equals(password)){
            SuccessBean successBean = new SuccessBean();
            successBean.setId(1);
            successBean.setName("su登录成功");
            responseBean.setCode(200);
            responseBean.setMessage("登录成功");
            responseBean.setData(successBean);
        }else{
            responseBean.setCode(404);
            responseBean.setMessage("登录失败");
            responseBean.setData(null);
        }
        Observable<ResponseBean> just = Observable.just(responseBean);
        return just;
    }
}
