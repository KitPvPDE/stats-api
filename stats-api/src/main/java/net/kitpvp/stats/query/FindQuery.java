package net.kitpvp.stats.query;

import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.sort.Sort;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface FindQuery<T> extends Iterable<T> {

    FindQuery<T> limit(int limit);

    FindQuery<T> skip(int skip);

    FindQuery<T> sort(Sort<T>[] filters);
    
    T first();

    @NotNull Iterator<T> iterator();


}
