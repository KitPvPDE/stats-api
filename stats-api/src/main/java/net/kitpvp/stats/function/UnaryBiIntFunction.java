package net.kitpvp.stats.function;

@FunctionalInterface
public interface UnaryBiIntFunction<T, R> {

    R apply(T t, int i, int j);

}
