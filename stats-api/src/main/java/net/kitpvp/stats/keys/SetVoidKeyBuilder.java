package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.builder.VoidStatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SetVoidKeyBuilder<X> implements VoidStatsKeyBuilder<Set<X>> {

    private final VoidKeyBuilder keyBuilder = new VoidKeyBuilder();
    private Supplier<Set<X>> def = HashSet::new;

    public SetVoidKeyBuilder<X> keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public SetVoidKeyBuilder<X> defaultNull() {
        this.def = () -> null;
        return this;
    }

    public SetVoidKeyBuilder<X> defaultValue(Supplier<Set<X>> constructor) {
        this.def = constructor;
        return this;
    }

    @Override
    public @NotNull SetVoidStatsKey<X> build() {
        return new SetVoidStatsKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetVoidSeasonKey<X> season() {
        return new SetVoidSeasonKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetVoidStageKey<X> stage(UnaryOperator<VoidKeyFunction> remapFunction) {
        return new SetVoidStageKeyImpl<>(this.def, this.keyBuilder.build(), remapFunction);
    }
}
