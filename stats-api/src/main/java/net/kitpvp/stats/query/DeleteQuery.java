package net.kitpvp.stats.query;

import net.kitpvp.stats.query.filter.Filter;
import org.jetbrains.annotations.NotNull;

public interface DeleteQuery<T> {

    DeleteQuery<T> filter(@NotNull Filter<T>[] filters);
}
