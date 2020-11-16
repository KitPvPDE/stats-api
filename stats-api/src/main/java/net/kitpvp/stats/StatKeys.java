package net.kitpvp.stats;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.array.ArraySStatsKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.keys.numeric.*;

import java.util.function.Function;

public class StatKeys {

    public static <V> SStatsKey<V> newStatsKey(String path, V def) {
        return null;
    }

    public static DoubleSStatsKey newStatsKey(String path, double def) {
        return null;
    }

    public static LongSStatsKey newStatsKey(String path, long offset) {
        return LongSStatsKey.builder().keyBuilder(builder->builder.path(path)).offset(offset).build();
    }

    public static IntSStatsKey newStatsKey(String path, int offset) {
        return IntSStatsKey.builder().keyBuilder(builder->builder.path(path)).offset(offset).build();
    }

    public static <V> ArraySStatsKey<V> newListKey(String path) {
        return ArraySStatsKey.<V>builder().keyBuilder(builder -> builder.path(path)).build();
    }

    public static <K, V> ArrayStatsKey<K, V> newListKey(String prefix, Function<K, String> function, String suffix) {
        return ArrayStatsKey.<K, V>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).build();
    }

    public static <K> LongStatsKey<K> newStatsKey(String prefix, Function<K, String> function, String suffix, long offset) {
        return LongStatsKey.<K>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).offset(offset).build();
    }

    public static LongSSeasonKey newSeasonKey(String path, long offset) {
        return LongSStatsKey.builder().keyBuilder(builder -> builder.path(path)).offset(offset).season();
    }

    public static <K> LongSeasonKey<K> newSeasonKey(String prefix, Function<K, String> function, String suffix, long offset) {
        return LongStatsKey.<K>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).offset(offset).season();
    }

    public static IntSSeasonKey newSeasonKey(String path, int offset) {
        return IntSStatsKey.builder().keyBuilder(builder -> builder.path(path)).offset(offset).season();
    }

    public static <K> IntSeasonKey<K> newSeasonKey(String prefix, Function<K, String> function, String suffix, int offset) {
        return IntStatsKey.<K>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).offset(offset).season();
    }
}
