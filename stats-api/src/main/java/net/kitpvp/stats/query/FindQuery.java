package net.kitpvp.stats.query;

import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.sort.Sort;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface FindQuery<T, R> extends Iterable<R> {

    FindQuery<T, R> limit(int limit);

    FindQuery<T, R> skip(int skip);

    FindQuery<T, R> sort(Sort<T>[] filters);

    FindQuery<T, R> filter(Filter<T>[] filters);
    
    R first();

    @NotNull Iterator<R> iterator();


}
