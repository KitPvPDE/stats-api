package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.functions.BindKeyFunction;
import net.kitpvp.stats.api.functions.season.PrefixedKeyFunction;
import net.kitpvp.stats.api.functions.season.PrefixedVoidKeyFunction;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import net.kitpvp.stats.season.Season;


public class KeyFunctions {

    public static <K> VoidKeyFunction bind(KeyFunction<K> function, K key) {
        return new BindKeyFunction<>(function, key);
    }

    public static <K> KeyFunction<K> alltime(KeyFunction<K> function) {
        return new SeasonKeyFunction<>(function, Season.ALLTIME);
    }

    public static <K> KeyFunction<K> season(KeyFunction<K> function) {
        return new SeasonKeyFunction<>(function, Season.getSeason());
    }

    public static <K> KeyFunction<K> season(int season, KeyFunction<K> function) {
        return new SeasonKeyFunction<>(function, season);
    }

    public static <K> KeyFunction<K> prefixed(KeyFunction<K> function, String prefix) {
        return new PrefixedKeyFunction<>(function, prefix);
    }

    public static VoidKeyFunction prefixed(VoidKeyFunction function, String prefix) {
        return new PrefixedVoidKeyFunction(function, prefix);
    }
}
