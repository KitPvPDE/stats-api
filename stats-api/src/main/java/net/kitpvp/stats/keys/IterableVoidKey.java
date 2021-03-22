package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface IterableVoidKey extends IterableKey<Void> {

    @Override
    Stream<? extends VoidKey> stream();
}
