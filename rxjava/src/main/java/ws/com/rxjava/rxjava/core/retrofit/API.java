package ws.com.rxjava.rxjava.core.retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface API {

    @GET("WS_Administration/login?phone=1889767259&password=e10adc3949ba59abbe56e057f20f883")
    Observable<LoginBean> getUserInfo();
}
