package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.functions.BindKeyFunction;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import net.kitpvp.stats.api.functions.season.StageKeyFunction;
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

    public static <K> KeyFunction<K> stage(KeyFunction<K> function) {
        return new StageKeyFunction<>(function, Season.getSeason(), Season.getStage());
    }

    public static <K> KeyFunction<K> stage(int season, int stage, KeyFunction<K> function) {
        return new StageKeyFunction<>(function, season, stage);
    }
}
