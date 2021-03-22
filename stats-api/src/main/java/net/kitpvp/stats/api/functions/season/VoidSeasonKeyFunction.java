package net.kitpvp.stats.api.functions.season;

import net.kitpvp.stats.keys.VoidKeyFunction;

import java.util.function.Function;

public class VoidSeasonKeyFunction implements VoidKeyFunction {

    private final VoidKeyFunction function;
    private final String prefix;

    public VoidSeasonKeyFunction(VoidKeyFunction function, int season) {
        this.prefix = (season == 0 ? "alltime" : "seasons.season" + season) + ".";
        this.function = function;
    }

    @Override
    public String prefix() {
        return this.prefix + this.function.prefix();
    }

    @Override
    public String key() {
        return this.key(null);
    }

    @Override
    public String key(Void key) {
        return this.prefix + this.function.key(key);
    }

    @Override
    public String suffix() {
        return this.function.suffix();
    }

    @Override
    public Function<Void, String> function() {
        return this.function.function();
    }

    @Override
    public Function<String, Void> inverse() {
        throw new UnsupportedOperationException("void key");
    }
}
