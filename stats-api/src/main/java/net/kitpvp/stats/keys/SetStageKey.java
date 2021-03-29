package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.stream.Stream;

public interface SetStageKey<K, X> extends StageKey<K, Set<X>>, IterableSetKey<K, X> {

    @Override
    SetStatsKey<K, X> alltime();

    @Override
    SetStatsKey<K, X> season();

    @Override
    SetStatsKey<K, X> stage();

    @Override
    SetStatsKey<K, X> season(int season);

    @Override
    VoidStageKey<Set<X>> bind(K k);

    @Override
    default Stream<? extends SetStatsKey<K, X>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
