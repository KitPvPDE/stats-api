package net.kitpvp.stats.query;

import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.filter.Update;
import org.jetbrains.annotations.NotNull;

public interface CountQuery<T> {

    CountQuery<T> filter(@NotNull Filter<T>[] filters);

}
