package net.kitpvp.stats.api.functions.keys;

public interface KeyFunction<T> {

    String apply(T t);

    String prefix();

    String suffix();

}
