package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface VoidSeasonKey<V> extends IterableVoidKey, SeasonKey<Void, V> {

    VoidStatsKey<V> season();

    VoidStatsKey<V> alltime();

    VoidStatsKey<V> season(int season);

    @Override
    Stream<? extends VoidStatsKey<V>> stream();

    default VoidSeasonKey<V> bind(Void unused) {
        return this;
    }
}
