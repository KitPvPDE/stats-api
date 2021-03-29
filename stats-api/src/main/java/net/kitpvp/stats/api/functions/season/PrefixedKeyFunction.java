package net.kitpvp.stats.api.functions.season;

import net.kitpvp.stats.keys.KeyFunction;

import java.util.function.Function;

public class PrefixedKeyFunction<K> implements KeyFunction<K> {

    private final KeyFunction<K> function;
    private final Function<String, K> inverseFunction;
    private final String prefix;

    public PrefixedKeyFunction(KeyFunction<K> function, String prefix) {
        this.prefix = prefix + (prefix.endsWith(".") ? "" : ".");
        this.function = function;
        this.inverseFunction = (key) -> this.function.inverse().apply(key.substring(this.prefix.length()));
    }

    @Override
    public String prefix() {
        return this.prefix + this.function.prefix();
    }

    @Override
    public String key(K key) {
        return this.prefix + this.function.key(key);
    }

    @Override
    public String suffix() {
        return this.function.suffix();
    }

    @Override
    public Function<K, String> function() {
        return this.function.function();
    }

    @Override
    public Function<String, K> inverse() {
        return this.inverseFunction;
    }
}
