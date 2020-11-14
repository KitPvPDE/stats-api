package net.kitpvp.stats.keys.impl.set;

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

    public SetVoidSeasonKeyImpl(@Nullable BiFunction<Supplier<Set<X>>, Function<Void, String>, SetSStatsKey<X>> keyConstructor, @Nullable Supplier<Set<X>> defaultFunction, @NotNull Function<Void, String> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public SetVoidSeasonKeyImpl(@NotNull Function<Void, String> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected SetSStatsKey<X> createKey(int season) {
        return new SetVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }
}
