package net.kitpvp.stats.local.query.update.updates;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.local.query.update.LocalUpdate;
import net.kitpvp.stats.query.update.Action;

@RequiredArgsConstructor
class LocalUpdateImpl<K, V> implements LocalUpdate {

    private final AppendableKey<K, V> statsKey;
    private final K key;
    private final V value;
    private final Action action;

    @Override
    public LocalUpdate append(LocalStats localStats) {
        switch(this.action) {
            case INC:
            case DEC:
            case MUL:
                throw new UnsupportedOperationException();
            case SET:
                this.statsKey.append(key, value, localStats::setStatKey);
                break;
            case UNSET:
                this.statsKey.append(key, value, localStats::unsetStatKey);
                break;
        }
        return this;
    }
}
