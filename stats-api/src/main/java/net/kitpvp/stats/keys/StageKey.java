package net.kitpvp.stats.keys;

public interface StageKey<K, V> extends SeasonKey<K, V>,
        IterableStatsKey<K, V> {

    StatsKey<K, V> stage();

    VoidStageKey<V> bind(K k);
}
