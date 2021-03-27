package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ArrayVoidKeyBuilder<X> implements StatsKeyBuilder<Void, List<X>> {

    private final VoidKeyBuilder keyBuilder = new VoidKeyBuilder();
    private Supplier<List<X>> def = ArrayList::new;

    public ArrayVoidKeyBuilder<X> keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public ArrayVoidKeyBuilder<X> defaultNull() {
        this.def = () -> null;
        return this;
    }

    public ArrayVoidKeyBuilder<X> defaultValue(Supplier<List<X>> constructor) {
        this.def = constructor;
        return this;
    }

    @Override
    public @NotNull ArrayVoidStatsKey<X> build() {
        return new ArrayVoidStatsKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArrayVoidSeasonKey<X> season() {
        return new ArrayVoidSeasonKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArrayVoidStageKey<X> stage() {
        return new ArrayVoidStageKeyImpl<>(this.def, this.keyBuilder.build());
    }
}
