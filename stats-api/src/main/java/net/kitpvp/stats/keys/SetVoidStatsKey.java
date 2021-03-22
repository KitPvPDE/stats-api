package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.Set;
import java.util.stream.Stream;

public interface SetVoidStatsKey<X> extends SetStatsKey<Void, X>, VoidStatsKey<Set<X>> {

    static <X> SetVoidKeyBuilder<X> builder() {
        return new SetVoidKeyBuilder<>();
    }

    @Override
    Set<X> def();

    @Override
    String key(Void unused);

    @Override
    Set<X> apply(Set<X> vs);

    @Override
    VoidKeyFunction keyFunction();

    @Override
    default VoidStatsKey<Set<X>> bind(Void unused) {
        return VoidStatsKey.super.bind(unused);
    }

    @Override
    default Stream<? extends SetVoidStatsKey<X>> stream() {
        return Stream.of(this);
    }
}
