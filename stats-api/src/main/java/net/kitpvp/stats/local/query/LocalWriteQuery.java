package net.kitpvp.stats.local.query;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.query.update.Update;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LocalWriteQuery {

    private final LocalStats localStats;

    @SafeVarargs
    public final LocalWriteQuery update(@NotNull Update<LocalStats>... updates) {
        Objects.requireNonNull(updates, "updates");
        Stream.of(updates).forEach(update -> update.append(this.localStats));
        return this;
    }
}
