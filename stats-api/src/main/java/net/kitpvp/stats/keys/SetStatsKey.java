package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;
import net.kitpvp.stats.reader.Reader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
    default Stream<? extends SetStatsKey<K, X>> stream() {
        return Stream.of(this);
    }

    @Override
    default Set<X> extract(Reader statsReader, K key) {
        List<X> list = statsReader.find(this.key(key), null);
        if(list == null)
            return this.def();

        return new HashSet<>(list);
    }
}
