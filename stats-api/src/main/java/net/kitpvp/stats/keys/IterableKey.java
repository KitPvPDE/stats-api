package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.stream.Stream;

public interface IterableKey<K> {

    Stream<? extends Key<K>> stream();
}
