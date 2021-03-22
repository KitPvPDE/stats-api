package net.kitpvp.stats.local.model;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.IterableStatsKey;
import net.kitpvp.stats.keys.IterableNumericStatsKey;
import net.kitpvp.stats.local.LocalStats;
import net.kitpvp.stats.local.query.LocalUpdate;
import net.kitpvp.stats.query.update.Action;
import net.kitpvp.stats.query.update.ArrayAction;

import java.util.Collection;
import java.util.List;

public final class Updates {

    public static <K, V> LocalUpdate set(IterableStatsKey<K, V> statsKey, K key, V value) {
        return new LocalUpdateImpl<K, V>(statsKey, key, value, Action.SET);
    }

    public static <K, V> LocalUpdate unset(IterableStatsKey<K, V> statsKey, K key, V value) {
        return new LocalUpdateImpl<>(statsKey, key, value, Action.UNSET);
    }

    public static <K, V> LocalUpdate inc(IterableNumericStatsKey<K, V> statsKey, K key, V value) {
        return new LocalNumericUpdateImpl<>(statsKey, key, value, Action.INC);
    }

    public static <K, V> LocalUpdate dec(IterableNumericStatsKey<K, V> statsKey, K key, V value) {
        return new LocalNumericUpdateImpl<K, V>(statsKey, key, value, Action.DEC);
    }

    public static <K, X> LocalUpdate push(IterableStatsKey<K, ? extends Collection<X>> statsKey, K key, List<X> value) {
        return new LocalArrayUpdateImpl<>(statsKey, key, value, ArrayAction.PUSH);
    }

    public static <K, X> LocalUpdate pull(IterableStatsKey<K, ? extends Collection<X>> statsKey, K key, List<X> value) {
        return new LocalArrayUpdateImpl<>(statsKey, key, value, ArrayAction.PULL);
    }

    @RequiredArgsConstructor
    static class LocalArrayUpdateImpl<K, X> implements LocalUpdate {

        private final IterableStatsKey<K, ? extends Collection<X>> statsKey;
        private final K key;
        private final List<X> value;
        private final ArrayAction operator;

        @Override
        public void update(LocalStats localStats) {
            switch (this.operator) {
                case PULL:
                    this.statsKey.stream().forEach((statsKey) -> {
                        Collection<X> current = localStats.getStatKey(statsKey, this.key);
                        current.removeAll(this.value);
                    });
                    break;
                case PUSH:
                    this.statsKey.stream().forEach((statsKey) -> {
                        Collection<X> current = localStats.getStatKey(statsKey, this.key);
                        current.addAll(this.value);
                    });
                    break;
            }
        }
    }

    @RequiredArgsConstructor
    static class LocalUpdateImpl<K, V> implements LocalUpdate {

        private final IterableStatsKey<K, V> statsKey;
        private final K key;
        private final V value;
        private final Action action;

        @Override
        public void update(LocalStats localStats) {
            switch (this.action) {
                case INC:
                case DEC:
                case MUL:
                    throw new UnsupportedOperationException();
                case SET:
                    this.statsKey.stream().forEach((statsKey) -> localStats.setStatKey(statsKey, this.key, this.value));
                    break;
                case UNSET:
                    this.statsKey.stream().forEach((statsKey) -> localStats.unsetStatKey(statsKey, this.key));
                    break;
            }
        }
    }

    @RequiredArgsConstructor
    static class LocalNumericUpdateImpl<K, V> implements LocalUpdate {

        private final IterableNumericStatsKey<K, V> statsKey;
        private final K key;
        private final V value;
        private final Action action;

        @Override
        public void update(LocalStats localStats) {
            switch (this.action) {
                case UNSET:
                case SET:
                case MUL:
                    throw new UnsupportedOperationException();
                case INC:
                    this.statsKey.stream().forEach(statsKey -> {
                        V current = localStats.getStatKey(statsKey, this.key);
                        localStats.setStatKey(statsKey, this.key, statsKey.addition().apply(current, this.value));
                    });
                    break;
                case DEC:
                    this.statsKey.stream().forEach(statsKey -> {
                        V current = localStats.getStatKey(statsKey, this.key);
                        localStats.setStatKey(statsKey, this.key, statsKey.addition().apply(current, statsKey.inverse().apply(this.value)));
                    });
                    break;
            }
        }

    }
}
