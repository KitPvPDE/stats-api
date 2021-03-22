package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.stream.Stream;

public interface BooleanSeasonKey<K> extends SeasonKey<K, Boolean> {

    @Override
    BooleanStatsKey<K> alltime();

    @Override
    BooleanStatsKey<K> season();

    @Override
    BooleanStatsKey<K> season(int season);

    @Override
    BooleanVoidSeasonKey bind(K k);

    @Override
    default Stream<? extends BooleanStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
