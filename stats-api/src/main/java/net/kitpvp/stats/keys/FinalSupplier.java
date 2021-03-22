package net.kitpvp.stats.keys;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
class FinalSupplier<V> implements Supplier<V> {

    private final V v;

    @Override
    public V get() {
        return this.v;
    }
}
