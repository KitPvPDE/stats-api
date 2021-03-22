package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.ComponentBuilder;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.impl.KeyImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class KeyBuilder<K> implements ComponentBuilder<KeyFunction<K>> {

    public static <K> KeyBuilder<K> builder() {
        return new KeyBuilder<>();
    }

    private String prefix;
    private Function<K, String> function;
    private Function<String, K> inverse;
    private String suffix;

    public KeyBuilder<K> key(@NotNull Key<K> key) {
        return this.function(key);
    }

    public KeyBuilder<K> function(@NotNull Key<K> key) {
        return this.function(key.keyFunction().function()).inverse(key.keyFunction().inverse());
    }

    public KeyBuilder<K> prefix(@Nullable String prefix) {
        this.prefix = prefix;
        return this;
    }

    public KeyBuilder<K> function(@Nullable Function<K, String> function) {
        this.function = function;
        return this;
    }

    public KeyBuilder<K> inverse(@Nullable Function<String, K> inverse) {
        this.inverse = inverse;
        return this;
    }

    public KeyBuilder<K> suffix(@Nullable String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Override
    public @NotNull KeyFunction<K> build() {
        if(this.function == null || this.inverse == null)
            throw new UnsupportedOperationException("no function specified");

        return new KeyFunctionImpl<>(this.prefix, this.function, this.inverse, this.suffix);
    }

    public @NotNull Key<K> buildKey() {
        return new KeyImpl<>(this.build());
    }

    @RequiredArgsConstructor
    private static class KeyFunctionImpl<K> implements KeyFunction<K> {

        private final @Nullable String prefix;
        private final @NotNull Function<K, String> function;
        private final @NotNull Function<String, K> inverse;
        private final @Nullable String suffix;

        @Override
        public String key(K k) {
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

        @Override
        public Function<K, String> function() {
            return this.function;
        }

        @Override
        public Function<String, K> inverse() {
            return this.inverse;
        }

        @Override
        public K inverse(String key) {
            return this.inverse.apply(key);
        }
    }
}
