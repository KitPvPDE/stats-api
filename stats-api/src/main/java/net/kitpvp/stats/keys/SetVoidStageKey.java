package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.Set;
import java.util.stream.Stream;

public interface SetVoidStageKey<X> extends SetStageKey<Void, X>, VoidStageKey<Set<X>> {

    @Override
    SetVoidStatsKey<X> alltime();

    @Override
    SetVoidStatsKey<X> season();

    @Override
    SetVoidStatsKey<X> stage();

    @Override
    SetVoidStatsKey<X> season(int season);

    @Override
    SetVoidStatsKey<X> stage(int season, int stage);

    @Override
    default SetVoidStageKey<X> bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends SetVoidStatsKey<X>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
