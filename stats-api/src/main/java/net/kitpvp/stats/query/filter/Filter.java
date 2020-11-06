package net.kitpvp.stats.query.filter;

public interface Filter<T> {

    Filter<T> append(T t);

}
