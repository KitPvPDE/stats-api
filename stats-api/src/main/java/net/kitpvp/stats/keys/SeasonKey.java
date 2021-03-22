package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;
import net.kitpvp.stats.season.Season;

import java.util.function.Consumer;
import java.util.stream.Stream;

public interface SeasonKey<K, V> extends IterableStatsKey<K, V> {

    StatsKey<K, V> alltime();

    StatsKey<K, V> season();

    StatsKey<K, V> season(int season);

    VoidSeasonKey<V> bind(K k);

    @Override
    Stream<? extends StatsKey<K, V>> stream();
}
