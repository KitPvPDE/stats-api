package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.builder.set.SetKeyBuilder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.Reader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    default Set<X> extract(Reader statsReader, K key) {
        List<X> list = statsReader.find(this.key(key), null);
        if(list == null)
            return this.def();

        return new HashSet<>(list);
    }
}
