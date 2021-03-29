package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.builder.VoidStatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class BooleanVoidKeyBuilder implements VoidStatsKeyBuilder<Boolean> {

    protected final VoidKeyBuilder keyBuilder;
    protected boolean def;

    BooleanVoidKeyBuilder() {
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
    public @NotNull BooleanVoidStatsKey build() {
        return new BooleanVoidStatsKeyImpl(this.keyBuilder.build(), this.def);
    }

    @Override
    public @NotNull BooleanVoidSeasonKey season() {
        return new BooleanVoidSeasonKeyImpl(this.keyBuilder.build(), this.def);
    }

    @Override
    public @NotNull BooleanVoidStageKey stage(UnaryOperator<VoidKeyFunction> remapFunction) {
        return new BooleanVoidStageKeyImpl(this.keyBuilder.build(), remapFunction, this.def);
    }
}
