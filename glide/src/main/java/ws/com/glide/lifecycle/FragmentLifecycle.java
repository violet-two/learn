package ws.com.glide.lifecycle;

import java.util.ArrayList;
import java.util.List;

public class FragmentLifecycle implements Lifecycle {

    private List<LifecycleListener> lifecycleListenerList = new ArrayList<>();

    @Override
    public void addListener(LifecycleListener listener) {
        lifecycleListenerList.add(listener);
    }

    @Override
    public void removeLifecycle(LifecycleListener listener) {
        lifecycleListenerList.remove(listener);
    }

    public void onStart() {
        for (LifecycleListener lifecycleListener : lifecycleListenerList) {
            lifecycleListener.onStart();
        }
    }

    public void onDestroy() {
        for (LifecycleListener lifecycleListener : lifecycleListenerList) {
            lifecycleListener.onDestroy();
        }
    }
}
