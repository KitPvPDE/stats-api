package net.kitpvp.stats.api.keys;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Entry<V, T> {

    public static <K, V> Entry<K, V> entry(K key, V value) {
        return new Entry<>(key, value);
    }

    private final V key;
    private final T value;
}