package com.example.ui_control.customView.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.R;
import com.example.ui_control.customView.BubbleSurfaceView;
import com.example.ui_control.customView.CustomDrawView;

public class ViewPagerFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    public ViewPagerFragment() {
    }

    public static Fragment newInstance(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        Bundle arguments = getArguments();
        if (arguments.getInt("position") == 1) {
            view = inflater.inflate(R.layout.custom_view_one, container, false);
        }
        if (arguments.getInt("position") == 2) {
            view = inflater.inflate(R.layout.custom_view_draw, container, false);
            CustomDrawView myView = view.findViewById(R.id.myView);
            getLifecycle().addObserver(myView);

        }
        if (arguments.getInt("position") == 3) {
            view = inflater.inflate(R.layout.custom_find_me, container, false);
        }
        if (arguments.getInt("position") == 4) {
            view = inflater.inflate(R.layout.custom_surface_view, container, false);
            SeekBar seekBar = view.findViewById(R.id.seekBar);
            seekBar.setOnSeekBarChangeListener(this);
        }
        return view;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        BubbleSurfaceView.changeDiscretePathEffect((float) i, (float) i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
