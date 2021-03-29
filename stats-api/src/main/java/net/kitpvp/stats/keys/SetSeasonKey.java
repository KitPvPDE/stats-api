package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.stream.Stream;

public interface SetSeasonKey<K, X> extends SeasonKey<K, Set<X>>, IterableSetKey<K, X> {

    @Override
    SetStatsKey<K, X> alltime();

    @Override
    SetStatsKey<K, X> season();

    @Override
    SetStatsKey<K, X> season(int season);

    @Override
    SetVoidSeasonKey<X> bind(K k);

    @Override
    default Stream<? extends SetStatsKey<K, X>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
