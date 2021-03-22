package net.kitpvp.stats.keys;

public interface NumericStageKey<K, V> extends StageKey<K, V>, NumericSeasonKey<K, V> {

    @Override
    NumericVoidStageKey<V> bind(K k);
}
