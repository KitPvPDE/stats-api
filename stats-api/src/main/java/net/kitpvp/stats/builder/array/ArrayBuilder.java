package net.kitpvp.stats.builder.array;

import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.builder.builders.NormalBuilder;

import java.util.function.Supplier;

public class ArrayBuilder<K, V> extends NormalBuilder<K, V> {

    public ArrayBuilder(KeyBuilder<K> keyBuilder, Supplier<V> defaultSupplier) {
        super(keyBuilder, defaultSupplier);
    }
}
