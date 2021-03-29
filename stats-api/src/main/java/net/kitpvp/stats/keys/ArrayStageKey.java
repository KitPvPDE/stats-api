package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface ArrayStageKey<K, X> extends StageKey<K, List<X>>, IterableArrayKey<K, X> {

    @Override
    ArrayStatsKey<K, X> alltime();

    @Override
    ArrayStatsKey<K, X> season();

    @Override
    ArrayStatsKey<K, X> stage();

    @Override
    ArrayStatsKey<K, X> season(int season);

    @Override
    ArrayVoidStageKey<X> bind(K k);

    @Override
    default Stream<? extends ArrayStatsKey<K, X>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
