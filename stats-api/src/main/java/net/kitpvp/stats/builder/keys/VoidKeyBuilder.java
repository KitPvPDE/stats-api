package net.kitpvp.stats.builder.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class VoidKeyBuilder extends KeyBuilder<Void> {

    public VoidKeyBuilder path(@NotNull String path) {
        return this.prefix(path).function(null).suffix(null);
    }

    @Override
    public VoidKeyBuilder prefix(@Nullable String prefix) {
        return (VoidKeyBuilder) super.prefix(prefix);
    }

    @Override
    public VoidKeyBuilder suffix(@Nullable String suffix) {
        return (VoidKeyBuilder) super.suffix(suffix);
    }

    @Override
    public VoidKeyBuilder function(@Nullable Function<Void, String> function) {
        return (VoidKeyBuilder) super.function(function);
    }
}
