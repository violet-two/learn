package com.example.ui_control.customView;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleObserver;

import com.example.ui_control.R;

public class CustomDrawViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_draw);
        CustomDrawView myView = findViewById(R.id.myView);
        getLifecycle().addObserver(myView);
    }
}
