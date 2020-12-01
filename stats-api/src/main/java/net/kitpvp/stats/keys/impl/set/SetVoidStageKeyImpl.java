package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;
import net.kitpvp.stats.keys.set.SetSStageKey;
import net.kitpvp.stats.keys.set.SetSStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class SetVoidStageKeyImpl<X> extends VoidStageKeyImpl<Set<X>, SetSStatsKey<X>> implements SetSStageKey<X> {

    public SetVoidStageKeyImpl(@Nullable BiFunction<Supplier<Set<X>>, KeyFunction<Void>, SetSStatsKey<X>> keyConstructor, @Nullable Supplier<Set<X>> defaultFunction, @NotNull KeyFunction<Void> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public SetVoidStageKeyImpl(@NotNull KeyFunction<Void> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected SetSStatsKey<X> createKey(int season) {
        return new SetVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }

    @Override
    protected SetSStatsKey<X> createKey(int season, int stage) {
        return new SetVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season, stage));
    }
}
