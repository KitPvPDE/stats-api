package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface VoidStatsKey<V> extends StatsKey<Void, V>, VoidKey, IterableVoidStatsKey<V> {

    static <V> VoidBuilder<V> builder() {
        return new VoidBuilder<>();
    }

    @Override
    VoidKeyFunction keyFunction();

    @Override
    V def();

    @Override
    V apply(V v);

    @Override
    default VoidStatsKey<V> bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends VoidStatsKey<V>> stream() {
        return Stream.of(this);
    }

    @Override
    default String key(Void key) {
        return this.keyFunction().key();
    }

    default String key() {
        return this.keyFunction().key();
    }
}
