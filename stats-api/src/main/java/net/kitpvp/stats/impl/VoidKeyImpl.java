package net.kitpvp.stats.impl;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.api.keys.VoidKey;

@RequiredArgsConstructor
public class VoidKeyImpl implements VoidKey {

    private final VoidKeyFunction function;

    @Override
    public VoidKeyFunction keyFunction() {
        return this.function;
    }
}
