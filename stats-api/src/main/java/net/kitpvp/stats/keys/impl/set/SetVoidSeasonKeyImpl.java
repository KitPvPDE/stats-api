package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;
import net.kitpvp.stats.keys.set.SetSSeasonKey;
import net.kitpvp.stats.keys.set.SetSStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetVoidSeasonKeyImpl<X> extends VoidSeasonKeyImpl<Set<X>, SetSStatsKey<X>> implements SetSSeasonKey<X> {

    public SetVoidSeasonKeyImpl(@Nullable BiFunction<Supplier<Set<X>>, KeyFunction<Void>, SetSStatsKey<X>> keyConstructor, @Nullable Supplier<Set<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public SetVoidSeasonKeyImpl(@NotNull VoidKeyFunction keyFunction) {
        super(keyFunction);
    }

    @Override
    protected SetSStatsKey<X> createKey(int season) {
        return new SetVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }
}
