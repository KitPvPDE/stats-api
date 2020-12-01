package net.kitpvp.stats.builder.keys;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.ComponentBuilder;
import net.kitpvp.stats.api.functions.keys.KeyFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class KeyBuilder<K> implements ComponentBuilder<KeyFunction<K>> {

    private String prefix;
    private Function<K, String> function;
    private String suffix;

    public KeyBuilder<K> prefix(@Nullable String prefix) {
        this.prefix = prefix;
        return this;
    }

    public KeyBuilder<K> function(@Nullable Function<K, String> function) {
        this.function = function;
        return this;
    }

    public KeyBuilder<K> suffix(@Nullable String suffix) {
        this.suffix = suffix;
        return this;
    }

    public KeyBuilder<Void> path(@NotNull String path) {
        return new VoidKeyBuilder().path(path);
    }

    @Override
    public @NotNull KeyFunction<K> build() {
        if(this.function == null) {
            if(this.prefix == null && this.suffix == null)
                throw new NullPointerException("Prefix and suffix are null");

            if(this.prefix != null && this.suffix != null) {
                return new StaticKey(this.prefix + "." + this.suffix);
            } else if(this.prefix != null) {
                return new StaticKey(this.prefix);
            } else{
                return new StaticKey(this.suffix);
            }
        }
        return new ParameterKey(this.prefix, this.function, this.suffix);
    }

    @RequiredArgsConstructor
    class ParameterKey implements KeyFunction<K> {
        private final String prefix;
        private final Function<K, String> function;
        private final String suffix;

        @Override
        public String apply(K k) {
            StringBuilder builder = new StringBuilder();
            if(this.prefix != null)
                builder.append(this.prefix).append(".");

            builder.append(this.function.apply(k));
            if(this.suffix != null)
                builder.append(".").append(this.suffix);

            return builder.toString();
        }

        @Override
        public String prefix() {
            return this.prefix;
        }

        @Override
        public String suffix() {
            return this.suffix;
        }
    }

    @RequiredArgsConstructor
    class StaticKey implements KeyFunction<K> {
        private final String key;

        @Override
        public String apply(K k) {
            return this.key;
        }

        @Override
        public String prefix() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String suffix() {
            throw new UnsupportedOperationException();
        }
    }
}
