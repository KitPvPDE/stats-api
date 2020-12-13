package net.kitpvp.stats.builder.bool;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.bool.BooleanSSeasonKey;
import net.kitpvp.stats.keys.bool.BooleanSStageKey;
import net.kitpvp.stats.keys.bool.BooleanSStatsKey;
import net.kitpvp.stats.keys.impl.bool.BooleanVoidSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.bool.BooleanVoidStageKeyImpl;
import net.kitpvp.stats.keys.impl.bool.BooleanVoidStatsKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BooleanVoidKeyBuilder implements StatsKeyBuilder<Void, Boolean> {

    protected final VoidKeyBuilder keyBuilder;
    protected boolean def;

    public BooleanVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public BooleanVoidKeyBuilder keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public BooleanVoidKeyBuilder def(boolean b) {
        this.def = b;
        return this;
    }

    @Override
    public @NotNull BooleanSStatsKey build() {
        return new BooleanVoidStatsKeyImpl(this.keyBuilder.build(), this.def);
    }

    @Override
    public @NotNull BooleanSSeasonKey season() {
        return new BooleanVoidSeasonKeyImpl(this.keyBuilder.build(), this.def);
    }

    @Override
    public @NotNull BooleanSStageKey stage() {
        return new BooleanVoidStageKeyImpl(this.keyBuilder.build(), this.def);
    }
}
