package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface BooleanVoidStatsKey extends BooleanStatsKey<Void>, VoidStatsKey<Boolean>,
        IterableBooleanVoidKey {

    static BooleanVoidKeyBuilder builder() {
        return new BooleanVoidKeyBuilder();
    }

    @Override
    Boolean def();

    @Override
    boolean defBoolean();

    @Override
    Boolean apply(Boolean integer);

    @Override
    boolean applyBoolean(boolean b);

    @Override
    String key(Void unused);

    @Override
    VoidKeyFunction keyFunction();

    @Override
    default Stream<? extends BooleanVoidStatsKey> stream() {
        return Stream.of(this);
    }

    @Override
    default BooleanVoidStatsKey bind(Void unused) {
        return this;
    }
}
