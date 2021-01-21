package net.kitpvp.stats.query.insert;

@FunctionalInterface
public interface Insert<T> {

    Insert<T> append(T t);
}
