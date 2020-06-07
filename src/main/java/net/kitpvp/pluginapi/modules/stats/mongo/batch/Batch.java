package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.pluginapi.modules.stats.Stats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Batch<T extends Stats> {

    Consumer<Void> EMPTY = (ignored) -> {};

    BatchAction getDefaultAction();

    T execute(boolean checkMain);

    void executeAsync(Consumer<Void> callback, Executor executor);

    <K, V> Batch<T> append(BatchAction action, StatsKey<K, V> statsKey, K k, V v);

    int size();

    default T execute() {
        return this.execute(true);
    }

    default void executeAsync() {
        this.executeAsync(EMPTY, Executors.DIRECT);
    }

    default void executeAsync(Consumer<Void> callback) {
        this.executeAsync(callback, Executors.DIRECT);
    }

    default <K, V> Batch<T> append(StatsKey<K, V> statsKey, K k, V v) {
        return this.append(this.getDefaultAction(), statsKey, k, v);
    }

    default <V> Batch<T> append(BatchAction action, SStatsKey<V> statsKey, V v) {
        return this.append(action, statsKey, null, v);
    }

    default <V> Batch<T> append(SStatsKey<V> statsKey, V v) {
        return this.append(this.getDefaultAction(), statsKey, v);
    }

    default <K, V> Batch<T> append(SeasonKey<K, V> seasonKey, K k, V v) {
        return this.append(seasonKey.alltime(), k, v).append(seasonKey.season(), k, v);
    }

    default <K, V> Batch<T> append(BatchAction action, SeasonKey<K, V> seasonKey, K k, V v) {
        return this.append(action, seasonKey.alltime(), k, v).append(action, seasonKey.season(), k, v);
    }

    default <V> Batch<T> append(SSeasonKey<V> seasonKey, V v) {
        return this.append(seasonKey.alltime(), v).append(seasonKey.season(), v);
    }

    default <V> Batch<T> append(BatchAction action, SSeasonKey<V> seasonKey, V v) {
        return this.append(action, seasonKey.alltime(), v).append(action, seasonKey.season(), v);
    }

    default <K, X, V> Batch<T> append(StatsKey<K, V> statsKey, K k, X x, Function<X, V> function) {
        return this.append(statsKey, k, function.apply(x));
    }

    default <K, X, V> Batch<T> append(StatsKey<K, List<V>> statsKey, K k, List<X> list, Function<X, V> function) {
        return this.append(statsKey, k, list.stream().map(function).collect(Collectors.toList()));
    }

    default <K, X, V> Batch<T> append(BatchAction action, StatsKey<K, V> statsKey, K k, X x, Function<X, V> function) {
        return this.append(action, statsKey, k, function.apply(x));
    }

    default <K, X, V> Batch<T> append(BatchAction action, StatsKey<K, List<V>> statsKey, K k, List<X> list, Function<X, V> function) {
        return this.append(action, statsKey, k, list.stream().map(function).collect(Collectors.toList()));
    }
}
