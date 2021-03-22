package net.kitpvp.stats.keys;

public interface StageKey<K, V> extends SeasonKey<K, V> {

    StatsKey<K, V> stage();

    StatsKey<K, V> stage(int season, int stage);

    VoidStageKey<V> bind(K k);
}
