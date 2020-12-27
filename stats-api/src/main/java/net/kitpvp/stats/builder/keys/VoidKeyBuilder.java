package net.kitpvp.stats.builder.keys;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.ComponentBuilder;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.api.keys.VoidKey;
import net.kitpvp.stats.impl.KeyImpl;
import net.kitpvp.stats.impl.VoidKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class VoidKeyBuilder implements ComponentBuilder<VoidKeyFunction> {

    private String path;

    public VoidKeyBuilder path(@NotNull String path) {
        this.path = path;
        return this;
    }

    @Override
    public @NotNull VoidKeyFunction build() {
        return new VoidKeyFunctionImpl(this.path);
    }

    public @NotNull VoidKey buildKey() {
        return new VoidKeyImpl(this.build());
    }

    @RequiredArgsConstructor
    private static class VoidKeyFunctionImpl implements VoidKeyFunction {

        private final Function<Void, String> function = x -> this.key();
        private final String key;

        @Override
        public String key() {
            return this.key;
        }

        @Override
        public String prefix() {
            throw new UnsupportedOperationException("void");
        }

        @Override
        public String suffix() {
            throw new UnsupportedOperationException("void");
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
}
