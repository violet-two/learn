package com.example.ui_control.popupWindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.ui_control.R;

public class PopupMenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
        Button button = findViewById(R.id.bn);
        PopupMenu popup = new PopupMenu(this,button);
        getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        popup.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.hideMenu:
                    popup.dismiss();
                    break;
                default:
                    Toast.makeText(this,"您点击了"+menuItem.getTitle(),Toast.LENGTH_SHORT).show();
            }
            return true;
        });
        button.setOnClickListener(view -> popup.show());
    }
}