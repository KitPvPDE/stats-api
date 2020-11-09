package net.kitpvp.stats.builder.array;

import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.builder.Builder;

import java.util.function.Supplier;

public class ArrayBuilder<K, V> extends Builder<K, V> {

    public ArrayBuilder(KeyBuilder<K> keyBuilder, Supplier<V> defaultSupplier) {
        super(keyBuilder, defaultSupplier);
    }
}
