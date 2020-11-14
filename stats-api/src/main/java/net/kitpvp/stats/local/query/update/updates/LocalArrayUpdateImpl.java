package net.kitpvp.stats.local.query.update.updates;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.local.query.update.LocalUpdate;
import net.kitpvp.stats.query.update.ArrayAction;

import java.util.List;

@RequiredArgsConstructor
class LocalArrayUpdateImpl<K, X> implements LocalUpdate {

    private final AppendableArrayKey<K, X> statsKey;
    private final K key;
    private final List<X> value;
    private final ArrayAction operator;

    @Override
    public LocalUpdate append(LocalStats localStats) {
        switch(this.operator) {
            case PULL:
                this.statsKey.append(this.key, this.value, (statsKey, k, v) -> {
                    List<X> current = localStats.getStatKey(statsKey, k);
                    current.removeAll(this.value);
                });
                break;
            case PUSH:
                this.statsKey.append(this.key, this.value, (statsKey, k, v) -> {
                    List<X> current = localStats.getStatKey(statsKey, k);
                    current.addAll(this.value);
                });
                break;
        }
        return this;
    }
}
