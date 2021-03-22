package net.kitpvp.stats.api.functions;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.KeyFunction;
import net.kitpvp.stats.keys.VoidKeyFunction;

import java.util.function.Function;

@RequiredArgsConstructor
public class BindKeyFunction<K> implements VoidKeyFunction {

    private final KeyFunction<K> keyFunction;
    private final Function<Void, String> function;
    private final K key;

    public BindKeyFunction(KeyFunction<K> keyFunction, K key) {
        this.keyFunction = keyFunction;
        this.function = (x) -> this.key();
        this.key = key;
    }

    @Override
    public String key() {
        return this.keyFunction.key(this.key);
    }

    @Override
    public String prefix() {
        return this.keyFunction.prefix();
    }

    @Override
    public String suffix() {
        return this.keyFunction.suffix();
    }

    @Override
    public Function<Void, String> function() {
        return this.function;
    }

    @Override
    public Function<String, Void> inverse() {
        throw new UnsupportedOperationException("void");
    }

    @Override
    public Void inverse(String key) {
        throw new UnsupportedOperationException("void");
    }
}
