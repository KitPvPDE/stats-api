package net.kitpvp.stats.builder.array;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.array.ArraySSeasonKey;
import net.kitpvp.stats.keys.array.ArraySStageKey;
import net.kitpvp.stats.keys.array.ArraySStatsKey;
import net.kitpvp.stats.keys.impl.ArrayVoidSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.ArrayVoidStageKeyImpl;
import net.kitpvp.stats.keys.impl.ArrayVoidStatsKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ArrayVoidKeyBuilder<X> implements StatsKeyBuilder<Void, List<X>> {

    private final VoidKeyBuilder keyBuilder = new VoidKeyBuilder();
    private Supplier<List<X>> def = ArrayList::new;

    public ArrayVoidKeyBuilder<X> keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public ArrayVoidKeyBuilder<X> defaultValue(Supplier<List<X>> constructor) {
        this.def = constructor;
        return this;
    }

    @Override
    public @NotNull ArraySStatsKey<X> build() {
        return new ArrayVoidStatsKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArraySSeasonKey<X> season() {
        return new ArrayVoidSeasonKeyImpl<>(ArrayVoidStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArraySStageKey<X> stage() {
        return new ArrayVoidStageKeyImpl<>(ArrayVoidStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }
}
