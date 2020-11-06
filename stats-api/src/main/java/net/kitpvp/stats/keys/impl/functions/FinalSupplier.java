package net.kitpvp.stats.keys.impl.functions;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class FinalSupplier<V> implements Supplier<V> {

    private final V v;

    @Override
    public V get() {
        return this.v;
    }
}
