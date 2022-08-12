package com.example.ui_control.customView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ui_control.R;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText editTextTextPersonName;
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_one);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName.addTextChangedListener(this);
        drawable = ContextCompat.getDrawable(this, R.drawable.ic_baseline_clear_24);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}