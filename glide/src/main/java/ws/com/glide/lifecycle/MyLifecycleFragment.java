package ws.com.glide.lifecycle;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyLifecycleFragment extends Fragment {

    FragmentLifecycle fragmentLifecycle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentLifecycle = new FragmentLifecycle();
    }


    public FragmentLifecycle getFragmentLifecycle() {
        return fragmentLifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentLifecycle.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentLifecycle.onDestroy();
    }
}
