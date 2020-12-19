package net.kitpvp.stats.api.functions.keys;

import java.util.function.Function;

public interface VoidKeyFunction extends KeyFunction<Void> {

    String key();

    @Override
    String prefix();

    @Override
    String suffix();

    @Override
    default String key(Void unused) {
        return this.key();
    }

    default Function<String, Void> inverse() {
        throw new UnsupportedOperationException("void key");
    }
}
