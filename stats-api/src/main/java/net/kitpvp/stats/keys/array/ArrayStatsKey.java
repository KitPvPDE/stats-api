package net.kitpvp.stats.keys.array;

import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.keys.StatsKey;

import java.util.List;

public interface ArrayStatsKey<K, X> extends StatsKey<K, List<X>>, AppendableArrayKey<K, X> {

    @Override
    List<X> def();

    @Override
    String key(K k);

    @Override
    List<X> apply(List<X> xes);
}
