package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface ArrayStatsKey<K, X> extends StatsKey<K, List<X>>, IterableArrayKey<K, X> {

    static <K, X> ArrayKeyBuilder<K, X> builder() {
        return new ArrayKeyBuilder<>();
    }

    @Override
    List<X> def();

    @Override
    String key(K k);

    @Override
    List<X> apply(List<X> xes);

    @Override
    ArrayVoidStatsKey<X> bind(K k);

    @Override
    default Stream<? extends ArrayStatsKey<K, X>> stream() {
        return Stream.of(this);
    }
}
