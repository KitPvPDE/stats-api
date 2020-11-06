package net.kitpvp.stats.query.filter;

@FunctionalInterface
public interface Update<T> {

    Update<T> append(T t);

}
