package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.set.SetStageKey;
import net.kitpvp.stats.keys.set.SetStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetStageKeyImpl<K, X> extends StageKeyImpl<K, Set<X>, SetStatsKey<K, X>> implements SetStageKey<K, X> {

    public SetStageKeyImpl(@Nullable BiFunction<Supplier<Set<X>>, Function<K, String>, SetStatsKey<K, X>> keyConstructor, @Nullable Supplier<Set<X>> defaultFunction, @NotNull Function<K, String> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public SetStageKeyImpl(@NotNull Function<K, String> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected SetStatsKey<K, X> createKey(int season) {
        return new SetStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }

    @Override
    protected SetStatsKey<K, X> createKey(int season, int stage) {
        return new SetStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season, stage));
    }
}
