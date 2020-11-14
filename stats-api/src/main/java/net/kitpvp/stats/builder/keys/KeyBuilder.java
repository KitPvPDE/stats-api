package net.kitpvp.stats.builder.keys;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.ComponentBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class KeyBuilder<K> implements ComponentBuilder<Function<K, String>> {

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
    public @NotNull Function<K, String> build() {
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
    class ParameterKey implements Function<K, String> {
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
    }

    @RequiredArgsConstructor
    class StaticKey implements Function<K, String> {
        private final String key;

        @Override
        public String apply(K k) {
            return this.key;
        }
    }
}
