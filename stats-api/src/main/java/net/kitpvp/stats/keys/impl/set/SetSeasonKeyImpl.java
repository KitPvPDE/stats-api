package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.keys.array.ArraySeasonKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.keys.impl.SeasonKeyImpl;
import net.kitpvp.stats.keys.set.SetSeasonKey;
import net.kitpvp.stats.keys.set.SetStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetSeasonKeyImpl<K, X> extends SeasonKeyImpl<K, Set<X>, SetStatsKey<K, X>> implements SetSeasonKey<K, X> {

    public SetSeasonKeyImpl(@Nullable BiFunction<Supplier<Set<X>>, Function<K, String>, SetStatsKey<K, X>> keyConstructor, @Nullable Supplier<Set<X>> defaultFunction, @NotNull Function<K, String> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public SetSeasonKeyImpl(@NotNull Function<K, String> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected SetStatsKey<K, X> createKey(int season) {
        return new SetStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }
}
