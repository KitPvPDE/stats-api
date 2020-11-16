package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.api.functions.TriConsumer;
import net.kitpvp.stats.season.Season;

import java.util.function.Function;

public interface StageKey<K, V> extends SeasonKey<K, V>, AppendableKey<K, V> {

    StatsKey<K, V> stage(int season, int stage);

    default StatsKey<K, V> stage() {
        return this.stage(Season.getSeason(), Season.getStage());
    }

    @Override
    default void append(K key, V value, TriConsumer<StatsKey<K, V>, K, V> function) {
        this.stage().append(key, value, function);

        SeasonKey.super.append(key, value, function);
    }
}
