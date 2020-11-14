package net.kitpvp.stats.local.query.update.updates;

import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.local.query.update.LocalUpdate;
import net.kitpvp.stats.query.update.Action;
import net.kitpvp.stats.query.update.ArrayAction;

import java.util.List;

public interface LocalUpdates {

    static <K, V> LocalUpdate set(AppendableKey<K, V> statsKey, K key, V value) {
        return new LocalUpdateImpl<>(statsKey, key, value, Action.SET);
    }

    static <K, V> LocalUpdate unset(AppendableKey<K, V> statsKey, K key, V value) {
        return new LocalUpdateImpl<>(statsKey, key, value, Action.UNSET);
    }

    static <K, V> LocalUpdate inc(AppendableIncKey<K, V> statsKey, K key, V value) {
        return new LocalNumericUpdateImpl<>(statsKey, key, value, Action.INC);
    }

    static <K, V> LocalUpdate dec(AppendableIncKey<K, V> statsKey, K key, V value) {
        return new LocalNumericUpdateImpl<>(statsKey, key, value, Action.DEC);
    }

    static <K, X> LocalUpdate push(AppendableArrayKey<K, X> statsKey, K key, List<X> value) {
        return new LocalArrayUpdateImpl<>(statsKey, key, value, ArrayAction.PUSH);
    }

    static <K, X> LocalUpdate pull(AppendableArrayKey<K, X> statsKey, K key, List<X> value) {
        return new LocalArrayUpdateImpl<>(statsKey, key, value, ArrayAction.PULL);
    }

}
