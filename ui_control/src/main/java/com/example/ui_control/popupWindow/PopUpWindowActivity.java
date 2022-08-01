package com.example.ui_control.popupWindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.ui_control.R;

public class PopUpWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);
        View root = this.getLayoutInflater().inflate(R.layout.popup_window,null);
        PopupWindow popup = new PopupWindow(root,1000,1000);
        Button show = findViewById(R.id.btn_show);
        show.setOnClickListener(view -> {
            popup.showAsDropDown(view);
            popup.showAtLocation(show, Gravity.CENTER,200,200);
            root.findViewById(R.id.close).setOnClickListener(view1->popup.dismiss());
        });
    }
}