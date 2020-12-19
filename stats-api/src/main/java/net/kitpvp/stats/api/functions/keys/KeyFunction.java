package net.kitpvp.stats.api.functions.keys;

import java.util.function.Function;

public interface KeyFunction<T> {

    String key(T t);

    String prefix();

    String suffix();

    Function<T, String> function();

    Function<String, T> inverse();

    default T inverse(String key) {
        return this.inverse().apply(key);
    }
}
