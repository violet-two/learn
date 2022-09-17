package ws.com.rxjava.rxjava.core;

import io.reactivex.rxjava3.annotations.NonNull;

public interface Function<@NonNull T,@NonNull U> {
    U apply(T t);
}
