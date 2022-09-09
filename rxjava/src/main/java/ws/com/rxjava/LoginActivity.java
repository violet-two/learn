package ws.com.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ws.com.rxjava.login.bean.SuccessBean;
import ws.com.rxjava.login.core.CustomObserver;
import ws.com.rxjava.login.core.LoginEngine;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginEngine.login("su","123456")
                .subscribe(new CustomObserver() {
                    @Override
                    public void success(SuccessBean successBean) {
                        Log.d(TAG, "success: "+successBean.toString());
                    }

                    @Override
                    public void error(String msg) {
                        Log.d(TAG, "success: "+msg);
                    }
                });
        LoginEngine.login("su1","1")
                .subscribe(new CustomObserver() {
                    @Override
                    public void success(SuccessBean successBean) {
                        Log.d(TAG, "success: "+successBean.toString());
                    }

                    @Override
                    public void error(String msg) {
                        Log.d(TAG, "error: "+msg);
                    }
                });
    }
}