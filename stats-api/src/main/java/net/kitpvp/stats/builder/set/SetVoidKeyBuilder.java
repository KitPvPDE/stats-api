package net.kitpvp.stats.builder.set;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.impl.set.SetVoidSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.set.SetVoidStageKeyImpl;
import net.kitpvp.stats.keys.impl.set.SetVoidStatsKeyImpl;
import net.kitpvp.stats.keys.set.SetSSeasonKey;
import net.kitpvp.stats.keys.set.SetSStageKey;
import net.kitpvp.stats.keys.set.SetSStatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetVoidKeyBuilder<X> implements StatsKeyBuilder<Void, Set<X>> {

    private final VoidKeyBuilder keyBuilder = new VoidKeyBuilder();
    private Supplier<Set<X>> def = HashSet::new;

    public SetVoidKeyBuilder<X> keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public SetVoidKeyBuilder<X> defaultValue(Supplier<Set<X>> constructor) {
        this.def = constructor;
        return this;
    }

    @Override
    public @NotNull SetSStatsKey<X> build() {
        return new SetVoidStatsKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetSSeasonKey<X> season() {
        return new SetVoidSeasonKeyImpl<>(SetVoidStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetSStageKey<X> stage() {
        return new SetVoidStageKeyImpl<>(SetVoidStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }
}
