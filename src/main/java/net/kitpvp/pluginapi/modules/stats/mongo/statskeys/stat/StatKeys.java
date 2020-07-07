package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.NumberKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.stat.NumKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.stat.SNumKey;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class StatKeys {

    public static <K, V> StatsKey<K, V> newStatsKey(String pre, Function<K, String> toKey, String post, V def) {
        return new StatKey<>(def, new ToKeyFunction<>(pre, post, toKey), null);
    }

    public static <K> NumberKey<K, Integer> newStatsKey(String pre, Function<K, String> toKey, String post, int def) {
        return new NumKey<>(new StatKey<>(def, new ToKeyFunction<>(pre, post, toKey), null), Integer::sum);
    }

    public static <K> NumberKey<K, Long> newStatsKey(String pre, Function<K, String> toKey, String post, long def) {
        return new NumKey<>(new StatKey<>(def, new ToKeyFunction<>(pre, post, toKey), null), Long::sum);
    }

    public static <K, V> StatsKey<K, V> newStatsKey(String pre, Function<K, String> toKey, String post, Supplier<V> toDefault) {
        return new StatKey<K, V>(toDefault, new ToKeyFunction<>(pre, post, toKey), null);
    }

    public static <V> SStatsKey<V> newStatsKey(String key, V def) {
        return new SStatKey<>(def, key, null);
    }

    public static SStatsKey<Long> newStatsKey(String key, long def) {
        return new SNumKey<>(new SStatKey<>(def, key, null), Long::sum);
    }

    public static SStatsKey<Integer> newStatsKey(String key, int def) {
        return new SNumKey<>(new SStatKey<>(def, key, null), Integer::sum);
    }

    public static <V> SStatsKey<V> newStatsKey(String key, Supplier<V> toDefault) {
        return new SStatKey<V>(toDefault, key, null);
    }

    public static <K, V extends Number> StatsKey<K, V> newStatsKey(String pre, Function<K, String> toKey, String post, V offset, V def, BiFunction<V, V, V> addFunction) {
        return new StatKey<>(def, new ToKeyFunction<>(pre, post, toKey), new AddFunction<>(addFunction, offset));
    }

    public static <V extends Number> SStatsKey<V> newStatsKey(String key, V offset, V def, BiFunction<V, V, V> addFunction) {
        return new SStatKey<>(def, key, new AddFunction<>(addFunction, offset));
    }

    public static <K> SeasonKey<K, Long> newSeasonKey(String pre, Function<K, String> toKey, String post, long def) {
        return new SeasonStatKey<K, Long>(def, new ToKeyFunction<>(pre, post, toKey), null, Long::sum);
    }

    public static <K, V> SeasonKey<K, V> newSeasonKey(String pre, Function<K, String> toKey, String post, V def) {
        return new SeasonStatKey<>(def, new ToKeyFunction<>(pre, post, toKey), null);
    }

    public static SSeasonKey<Long> newSeasonKey(String key, long def) {
        return new SSeasonStatKey<>(def, key, null, Long::sum);
    }

    public static SSeasonKey<Integer> newSeasonKey(String key, int def) {
        return new SSeasonStatKey<>(def, key, null, Integer::sum);
    }

    public static <V> SSeasonKey<V> newSeasonKey(String key, V def) {
        return new SSeasonStatKey<>(def, key, null);
    }

    public static <V> SSeasonKey<V> newSeasonKey(String key, Supplier<V> toDefault) {
        return new SSeasonStatKey<V>(toDefault, key, null);
    }

    public static <K, V extends Number> SeasonKey<K, V> newSeasonKey(String pre, Function<K, String> toKey, String post, V offset, V def, BiFunction<V, V, V> addFunction) {
        return new SeasonStatKey<>(def, new ToKeyFunction<>(pre, post, toKey), new AddFunction<>(addFunction, offset));
    }

    public static <V extends Number> SSeasonKey<V> newSeasonKey(String key, V offset, V def, BiFunction<V, V, V> addFunction) {
        return new SSeasonStatKey<>(def, key, new AddFunction<>(addFunction, offset));
    }

    public static SSeasonKey<Integer> newSeasonKey(String key, int def, int offset) {
        return new SSeasonStatKey<>(def, key, new AddFunction<>(Integer::sum, offset), Integer::sum);
    }

    public static SSeasonKey<Long> newSeasonKey(String key, long def, long offset) {
        return new SSeasonStatKey<Long>(def, key, new AddFunction<>(Long::sum, offset), Long::sum);
    }

    @RequiredArgsConstructor
    private static class AddFunction<V> implements Function<V, V> {
        private final BiFunction<V, V, V> add;
        private final V offset;

        @Override
        public V apply(V v) {
            return this.add.apply(v, this.offset);
        }
    }

    @RequiredArgsConstructor
    private static class ToKeyFunction<K> implements Function<K, String> {
        private final String pre, post;
        private final Function<K, String> function;

        @Override
        public String apply(K k) {
            return (this.pre != null ? (this.pre + ".") : "") +
                    function.apply(k) +
                    (this.post != null ? ("." + this.post) : "");
        }
    }
}
