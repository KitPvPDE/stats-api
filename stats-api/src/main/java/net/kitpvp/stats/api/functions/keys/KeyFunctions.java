package net.kitpvp.stats.api.functions.keys;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.season.Season;

import java.util.function.Function;
import java.util.function.Supplier;


public class KeyFunctions {

    public static <K> Function<Void, String> bind(Function<K, String> function, K key) {
        return new BoundKeyFunction<K>(key, function);
    }

    public static <K> Function<K, String> prepend(String prefix, Function<K, String> function) {
        return new AppendFunction<>(prefix, function);
    }

    public static <K> Function<K, String> immutable(String path) {
        return new ImmutableAppendFunction<>(path);
    }

    public static <K> Function<K, String> alltime(Function<K, String> function) {
        return new AppendFunction<>("alltime", function);
    }

    public static <K> Function<K, String> alltime() {
        return immutable("alltime");
    }

    public static <K> Function<K, String> season(Function<K, String> function) {
        return new DynamicAppendFunction<>(() -> "seasons.season" + Season.getSeason(), function);
    }

    public static <K> Function<K, String> season(int season, Function<K, String> function) {
        return new AppendFunction<>("seasons.season" + season, function);
    }

    public static <K> Function<K, String> stage(Function<K, String> function) {
        return new DynamicAppendFunction<>(() -> "seasons.season" + Season.getSeason() + ".stages.stage" + Season.getStage(), function);
    }

    public static <K> Function<K, String> stage(int season, int stage, Function<K, String> function) {
        return new AppendFunction<>("seasons.season" + season + ".stages.stage" + stage, function);
    }

    @RequiredArgsConstructor
    private static class DynamicAppendFunction<K> implements Function<K, String> {

        private final Supplier<String> appendix;
        private final Function<K, String> function;

        @Override
        public String apply(K k) {
            return this.appendix.get() + "." + this.function.apply(k);
        }
    }

    @RequiredArgsConstructor
    private static class AppendFunction<K> implements Function<K, String> {

        private final String appendix;
        private final Function<K, String> function;

        @Override
        public String apply(K k) {
            return this.appendix + "." + this.function.apply(k);
        }
    }

    @RequiredArgsConstructor
    private static class ImmutableAppendFunction<K> implements Function<K, String> {

        private final String appendix;

        @Override
        public String apply(K k) {
            return this.appendix;
        }
    }

    @RequiredArgsConstructor
    private static class BoundKeyFunction<K> implements Function<Void, String> {

        private final K key;
        private final Function<K, String> function;
        private final String result;

        public BoundKeyFunction(K key, Function<K, String> function) {
            this.key = key;
            this.function = function;
            this.result = this.function.apply(this.key);
        }

        @Override
        public String apply(Void unused) {
            return this.result;
        }
    }

}
