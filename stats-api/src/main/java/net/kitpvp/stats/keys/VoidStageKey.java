package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface VoidStageKey<V> extends StageKey<Void, V>, VoidSeasonKey<V> {

    @Override
    VoidStatsKey<V> alltime();

    @Override
    VoidStatsKey<V> season();

    @Override
    StatsKey<Void, V> stage();

    @Override
    StatsKey<Void, V> stage(int season, int stage);

    @Override
    VoidStatsKey<V> season(int season);

    @Override
    Stream<? extends VoidStatsKey<V>> stream();

    @Override
    default VoidStageKey<V> bind(Void unused) {
        return this;
    }
}
