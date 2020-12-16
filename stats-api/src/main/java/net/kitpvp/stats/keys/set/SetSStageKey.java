package net.kitpvp.stats.keys.set;

public interface SetSStageKey<X> extends SetStageKey<Void, X> {

    @Override
    SetSStatsKey<X> stage();

    @Override
    SetSStatsKey<X> stage(int season, int stage);
}
