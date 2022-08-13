package com.example.ui_control.customView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.example.ui_control.R;
import com.example.ui_control.customView.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SurfaceViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_surface_view);
    }

}