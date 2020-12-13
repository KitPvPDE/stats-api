package net.kitpvp.stats.keys.bool;

import net.kitpvp.stats.keys.StageKey;

public interface BooleanStageKey<K> extends BooleanSeasonKey<K>, StageKey<K, Boolean> {

    @Override
    BooleanStatsKey<K> season(int season);

    @Override
    BooleanStatsKey<K> season();

    @Override
    BooleanStatsKey<K> alltime();

    @Override
    BooleanStatsKey<K> stage(int season, int stage);

    @Override
    BooleanStatsKey<K> stage();
}
