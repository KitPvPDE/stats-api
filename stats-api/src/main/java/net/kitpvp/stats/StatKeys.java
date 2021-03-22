package net.kitpvp.stats;

import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.keys.ArrayVoidStatsKey;
import net.kitpvp.stats.keys.ArrayStatsKey;

import java.util.function.Function;

public class StatKeys {

    public static <V> VoidStatsKey<V> newStatsKey(String path, V def) {
        return VoidStatsKey.<V>builder().keyBuilder(builder -> builder.path(path)).defaultValue(def).build();
    }

    public static DoubleVoidStatsKey newStatsKey(String path, double offset) {
        return DoubleVoidStatsKey.builder().keyBuilder(builder->builder.path(path)).offset(offset).build();
    }

    public static LongVoidStatsKey newStatsKey(String path, long offset) {
        return LongVoidStatsKey.builder().keyBuilder(builder->builder.path(path)).offset(offset).build();
    }

    public static IntVoidStatsKey newStatsKey(String path, int offset) {
        return IntVoidStatsKey.builder().keyBuilder(builder->builder.path(path)).offset(offset).build();
    }

    public static <V> ArrayVoidStatsKey<V> newListKey(String path) {
        return ArrayVoidStatsKey.<V>builder().keyBuilder(builder -> builder.path(path)).build();
    }

    public static <K, V> ArrayStatsKey<K, V> newListKey(String prefix, Function<K, String> function, String suffix) {
        return ArrayStatsKey.<K, V>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).build();
    }

    public static <K> LongStatsKey<K> newStatsKey(String prefix, Function<K, String> function, String suffix, long offset) {
        return LongStatsKey.<K>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).offset(offset).build();
    }

    public static LongVoidSeasonKey newSeasonKey(String path, long offset) {
        return LongVoidStatsKey.builder().keyBuilder(builder -> builder.path(path)).offset(offset).season();
    }

    public static <K> LongSeasonKey<K> newSeasonKey(String prefix, Function<K, String> function, String suffix, long offset) {
        return LongStatsKey.<K>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).offset(offset).season();
    }

    public static IntVoidSeasonKey newSeasonKey(String path, int offset) {
        return IntVoidStatsKey.builder().keyBuilder(builder -> builder.path(path)).offset(offset).season();
    }

    public static <K> IntSeasonKey<K> newSeasonKey(String prefix, Function<K, String> function, String suffix, int offset) {
        return IntStatsKey.<K>builder().keyBuilder(builder -> builder.prefix(prefix).function(function).suffix(suffix)).offset(offset).season();
    }
}
