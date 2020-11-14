package net.kitpvp.stats.local.query.update;

import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.query.update.Update;

public interface LocalUpdate extends Update<LocalStats> {

    @Override
    LocalUpdate append(LocalStats localStats);
}
