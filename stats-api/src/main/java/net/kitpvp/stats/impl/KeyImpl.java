package net.kitpvp.stats.impl;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.keys.Key;

@RequiredArgsConstructor
public class KeyImpl<K> implements Key<K> {

    private final KeyFunction<K> keyFunction;

    @Override
    public KeyFunction<K> keyFunction() {
        return this.keyFunction;
    }
}
