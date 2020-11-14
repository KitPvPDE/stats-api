package net.kitpvp.stats.query;

import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.update.Update;
import org.jetbrains.annotations.NotNull;

public interface WriteQuery<T> {

    WriteQuery<T> filter(@NotNull Filter<T>[] filters);

    WriteQuery<T> update(@NotNull Update<T>[] updates);
}
