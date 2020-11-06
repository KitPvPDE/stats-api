package net.kitpvp.stats.keys.array;

import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.keys.SeasonKey;
import net.kitpvp.stats.keys.StatsKey;

import java.util.List;

public interface ArraySeasonKey<K, X> extends SeasonKey<K, List<X>>, AppendableArrayKey<K, X> {

    @Override
    ArrayStatsKey<K, X> season(int season);

    @Override
    default ArrayStatsKey<K, X> season() {
        return (ArrayStatsKey<K, X>) SeasonKey.super.season();
    }

    @Override
    default ArrayStatsKey<K, X> alltime() {
        return (ArrayStatsKey<K, X>) SeasonKey.super.alltime();
    }
}
