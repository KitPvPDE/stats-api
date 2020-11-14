package net.kitpvp.stats.local.query;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.query.WriteQuery;
import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.update.Update;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

@RequiredArgsConstructor
public class LocalWriteQuery implements WriteQuery<LocalStats> {

    private final LocalStats localStats;

    @Override
    public LocalWriteQuery filter(@NotNull Filter<LocalStats>[] filters) {
        return this;
    }

    @Override
    @SafeVarargs
    public final LocalWriteQuery update(@NotNull Update<LocalStats>... updates) {
        Preconditions.checkNotNull(updates, "updates");
        Stream.of(updates).forEach(update -> update.append(this.localStats));
        return this;
    }
}
