package net.kitpvp.stats.impl;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.VoidKey;
import net.kitpvp.stats.keys.VoidKeyFunction;

import java.util.stream.Stream;

@RequiredArgsConstructor
public class VoidKeyImpl implements VoidKey {

    private final VoidKeyFunction function;

    @Override
    public VoidKeyFunction keyFunction() {
        return this.function;
    }

    @Override
    public Stream<? extends VoidKey> stream() {
        return Stream.of(this);
    }
}
