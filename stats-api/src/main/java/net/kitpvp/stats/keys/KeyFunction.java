package net.kitpvp.stats.keys;

import java.util.function.Function;

public interface KeyFunction<T> {

    String key(T t);

    String prefix();

    String suffix();

    Function<T, String> function();

    Function<String, T> inverse();

    default VoidKeyFunction bind(T t) {
        return KeyFunctions.bind(this, t);
    }

    default T inverse(String key) {
        return this.inverse().apply(key);
    }
}
