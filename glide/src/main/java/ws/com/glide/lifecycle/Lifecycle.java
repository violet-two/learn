package ws.com.glide.lifecycle;

public interface Lifecycle {

    void addListener(LifecycleListener listener);
    void removeLifecycle(LifecycleListener listener);
}
