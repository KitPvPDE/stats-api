package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

public class SSeasonToKeyFunction<V> extends SeasonToKeyFunction<Void, V> {

    protected final SSeasonStatKey<V> seasonKey;

    public SSeasonToKeyFunction(SSeasonStatKey<V> seasonKey, int season) {
        super(seasonKey, season);

        this.seasonKey = seasonKey;
    }

    @Override
    public String apply(Void v) {
        return (this.season == 0 ? "alltime" : "seasons.season" + this.season) + "." + this.seasonKey.key;
    }
}
