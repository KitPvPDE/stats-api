package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.stream.Stream;

public interface BooleanStageKey<K> extends BooleanSeasonKey<K>, StageKey<K, Boolean> {

    @Override
    BooleanStatsKey<K> alltime();

    @Override
    BooleanStatsKey<K> season();

    @Override
    BooleanStatsKey<K> stage();

    @Override
    BooleanStatsKey<K> season(int season);

    @Override
    BooleanStatsKey<K> stage(int season, int stage);

    @Override
    BooleanVoidStageKey bind(K k);

    @Override
    default Stream<? extends BooleanStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
