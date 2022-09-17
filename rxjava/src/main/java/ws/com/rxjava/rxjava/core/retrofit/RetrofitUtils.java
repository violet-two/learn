package ws.com.rxjava.rxjava.core.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author su
 * Create by on 2022/9/17 15:59
 */
public class RetrofitUtils {

    private static API api;
    private static Retrofit retrofit;
    static {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://119.96.82.181:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static API getApi() {
        if(api==null){
            api = retrofit.create(API.class);
        }
        return api;
    }
}
