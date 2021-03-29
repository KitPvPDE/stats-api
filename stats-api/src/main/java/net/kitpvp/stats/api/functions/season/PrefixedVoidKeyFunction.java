package net.kitpvp.stats.api.functions.season;

import net.kitpvp.stats.keys.VoidKeyFunction;

import java.util.function.Function;

public class PrefixedVoidKeyFunction implements VoidKeyFunction {

    private final VoidKeyFunction function;
    private final String prefix;

    public PrefixedVoidKeyFunction(VoidKeyFunction function, String prefix) {
        this.prefix = prefix + (prefix.endsWith(".") ? "" : ".");
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
    public String key(Void ignored) {
        return this.prefix + this.function.key(ignored);
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
