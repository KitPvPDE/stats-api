package net.kitpvp.stats.local.query.update.updates;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.keys.IncStatsKey;
import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.local.query.update.LocalUpdate;
import net.kitpvp.stats.query.update.Action;

@RequiredArgsConstructor
public class LocalNumericUpdateImpl<K, V> implements LocalUpdate {

    private final AppendableIncKey<K, V> statsKey;
    private final K key;
    private final V value;
    private final Action action;

    @Override
    public LocalUpdate append(LocalStats localStats) {
        switch(this.action) {
            case UNSET:
            case SET:
            case MUL:
                throw new UnsupportedOperationException();
            case INC:
                this.statsKey.append(key, value, (statsKey, k, v) -> {
                    IncStatsKey<K, V> incKey = (IncStatsKey<K, V>) statsKey;
                    V current = localStats.getStatKey(statsKey, k);
                    V updated = incKey.addition().apply(current, v);

                    localStats.setStatKey(statsKey, k, updated);
                });
                break;
            case DEC:
                this.statsKey.append(key, value, (statsKey, k, v) -> {
                    IncStatsKey<K, V> incKey = (IncStatsKey<K, V>) statsKey;
                    V current = localStats.getStatKey(statsKey, k);
                    V updated = incKey.addition().apply(current, incKey.inverse().apply(v));

                    localStats.setStatKey(statsKey, k, updated);
                });
                break;
        }
        return this;
    }

}
