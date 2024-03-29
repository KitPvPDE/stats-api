package net.kitpvp.stats.api.functions.season;

import net.kitpvp.stats.keys.KeyFunction;

import java.util.function.Function;

public class SeasonKeyFunction<K> implements KeyFunction<K> {

    private final KeyFunction<K> function;
    private final Function<String, K> inverseFunction;
    private final String prefix;

    public SeasonKeyFunction(KeyFunction<K> function, int season) {
        this.prefix = (season == 0 ? "alltime" : "seasons.season" + season) + ".";
        this.function = function;
        this.inverseFunction = this.function::inverse;
    }

    public SeasonKeyFunction(KeyFunction<K> function, String prefix) {
        this.prefix = prefix + ".";
        this.function = function;
        this.inverseFunction = this.function::inverse;
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
