package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.builder.set.SetKeyBuilder;
import net.kitpvp.stats.keys.StatsKey;

import java.util.Set;

public interface SetStatsKey<K, X> extends StatsKey<K, Set<X>> {

    static <K, X> SetKeyBuilder<K, X> builder() {
        return new SetKeyBuilder<>();
    }

    @Override
    Set<X> def();

    @Override
    String key(K k);

    @Override
    Set<X> apply(Set<X> vs);
}
