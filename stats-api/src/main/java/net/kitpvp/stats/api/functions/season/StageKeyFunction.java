package net.kitpvp.stats.api.functions.season;

import com.google.common.base.Preconditions;
import net.kitpvp.stats.keys.KeyFunction;

import java.util.function.Function;

public class StageKeyFunction<K> implements KeyFunction<K> {

    private final KeyFunction<K> function;
    private final Function<String, K> inverseFunction;
    private final String prefix;

    public StageKeyFunction(KeyFunction<K> function, int season, int stage) {
        Preconditions.checkArgument(season > 0, "Season must be > 0");
        Preconditions.checkArgument(stage > 0, "Stage must be > 0");

        this.prefix = "seasons.season" + season + ".stages.stage" + stage + ".";
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
