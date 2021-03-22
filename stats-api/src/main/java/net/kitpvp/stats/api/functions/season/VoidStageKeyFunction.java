package net.kitpvp.stats.api.functions.season;

import com.google.common.base.Preconditions;
import net.kitpvp.stats.keys.VoidKeyFunction;

import java.util.function.Function;

public class VoidStageKeyFunction implements VoidKeyFunction {

    private final VoidKeyFunction function;
    private final String prefix;

    public VoidStageKeyFunction(VoidKeyFunction function, int season, int stage) {
        Preconditions.checkArgument(season > 0, "Season must be > 0");
        Preconditions.checkArgument(stage > 0, "Stage must be > 0");

        this.prefix = "seasons.season" + season + ".stages.stage" + stage + ".";
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
