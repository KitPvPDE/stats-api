package net.kitpvp.stats.api.functions.keys;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.season.Season;

import java.util.function.Function;
import java.util.function.Supplier;


public class KeyFunctions {

    public static <K> KeyFunction<Void> bind(KeyFunction<K> function, K key) {
        return new BoundKeyFunction<K>(key, function);
    }

    public static <K> KeyFunction<K> prepend(String prefix, KeyFunction<K> function) {
        return new AppendFunction<>(prefix, function);
    }

    public static <K> KeyFunction<K> immutable(String path) {
        return new ImmutableAppendFunction<>(path);
    }

    public static <K> KeyFunction<K> alltime(KeyFunction<K> function) {
        return new AppendFunction<>("alltime", function);
    }

    public static <K> KeyFunction<K> alltime() {
        return immutable("alltime");
    }

    public static <K> KeyFunction<K> season(KeyFunction<K> function) {
        return new DynamicAppendFunction<>(() -> "seasons.season" + Season.getSeason(), function);
    }

    public static <K> KeyFunction<K> season(int season, KeyFunction<K> function) {
        return new AppendFunction<>("seasons.season" + season, function);
    }

    public static <K> KeyFunction<K> stage(KeyFunction<K> function) {
        return new DynamicAppendFunction<>(() -> "seasons.season" + Season.getSeason() + ".stages.stage" + Season.getStage(), function);
    }

    public static <K> KeyFunction<K> stage(int season, int stage, KeyFunction<K> function) {
        return new AppendFunction<>("seasons.season" + season + ".stages.stage" + stage, function);
    }

    @RequiredArgsConstructor
    private static class DynamicAppendFunction<K> implements KeyFunction<K> {

        private final Supplier<String> appendix;
        private final KeyFunction<K> function;

        @Override
        public String apply(K k) {
            return this.appendix.get() + "." + this.function.apply(k);
        }

        @Override
        public String prefix() {
            return this.appendix.get() + "." + this.function.prefix();
        }

        @Override
        public String suffix() {
            return this.function.suffix();
        }
    }

    @RequiredArgsConstructor
    private static class AppendFunction<K> implements KeyFunction<K> {

        private final String appendix;
        private final KeyFunction<K> function;

        @Override
        public String apply(K k) {
            return this.appendix + "." + this.function.apply(k);
        }

        @Override
        public String prefix() {
            return this.appendix + "." + this.function.prefix();
        }

        @Override
        public String suffix() {
            return this.function.suffix();
        }
    }

    @RequiredArgsConstructor
    private static class ImmutableAppendFunction<K> implements KeyFunction<K> {

        private final String appendix;

        @Override
        public String apply(K k) {
            return this.appendix;
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

    @RequiredArgsConstructor
    private static class BoundKeyFunction<K> implements KeyFunction<Void> {

        private final K key;
        private final KeyFunction<K> function;
        private final String result;

        public BoundKeyFunction(K key, KeyFunction<K> function) {
            this.key = key;
            this.function = function;
            this.result = this.function.apply(this.key);
        }

        @Override
        public String apply(Void unused) {
            return this.result;
        }

        @Override
        public String prefix() {
            return this.function.prefix();
        }

        @Override
        public String suffix() {
            return this.function.suffix();
        }
    }

}
