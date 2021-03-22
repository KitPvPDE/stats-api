package net.kitpvp.stats.api.function;

@FunctionalInterface
public interface BiLongConsumer<T, U> {

    void accept(T t, U u, long l);
}
