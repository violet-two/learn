package com.example.ui_control.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.example.ui_control.R;

public class RatingBarActivity extends AppCompatActivity{

    private RatingBar rb_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        rb_bar = findViewById(R.id.rb_bar);
        findViewById(R.id.rb_bar).setOnClickListener((view)->{
            rb_bar.setProgress(0);
        });
    }

}