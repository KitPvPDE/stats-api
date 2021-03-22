package net.kitpvp.stats.impl;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.KeyFunction;
import net.kitpvp.stats.Key;

import java.util.stream.Stream;

@RequiredArgsConstructor
public class KeyImpl<K> implements Key<K> {

    private final KeyFunction<K> keyFunction;

    @Override
    public KeyFunction<K> keyFunction() {
        return this.keyFunction;
    }

    @Override
    public Stream<? extends Key<K>> stream() {
        return Stream.of(this);
    }
}
