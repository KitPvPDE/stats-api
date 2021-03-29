package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface IterableArrayKey<K, X> extends IterableStatsKey<K, List<X>> {

    @Override
    Stream<? extends ArrayStatsKey<K, X>> stream();
}
