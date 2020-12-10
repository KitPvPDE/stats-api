package net.kitpvp.stats.mongodb.query.update.updates;

import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.api.keys.AppendableSetKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.keys.set.SetStatsKey;
import net.kitpvp.stats.query.update.ArrayAction;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import net.kitpvp.stats.query.update.Action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public interface MongoUpdates {

    static <K, V> MongoUpdate set(AppendableKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.SET);
    }

    static <V> MongoUpdate set(AppendableKey<Void, V> statsKey, V v) {
        return new MongoUpdateImpl<>(statsKey, null, v, Action.SET);
    }

    static <K, V> MongoUpdate unset(AppendableKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.UNSET);
    }

    static <K, V> MongoUpdate inc(AppendableIncKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.INC);
    }

    static <V> MongoUpdate inc(AppendableIncKey<Void, V> statsKey, V v) {
        return new MongoUpdateImpl<>(statsKey, null, v, Action.INC);
    }

    static <K> MongoUpdate inc(AppendableIncKey<K, Integer> statsKey, K k, int v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.INC);
    }

    static MongoUpdate inc(AppendableIncKey<Void, Integer> statsKey, int v) {
        return new MongoUpdateImpl<>(statsKey, null, v, Action.INC);
    }

    static <K> MongoUpdate inc(AppendableIncKey<K, Long> statsKey, K k, long v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.INC);
    }

    static MongoUpdate inc(AppendableIncKey<Void, Long> statsKey, long v) {
        return new MongoUpdateImpl<>(statsKey, null, v, Action.INC);
    }


    static <K, V> MongoUpdate dec(AppendableIncKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.DEC);
    }

    static <K, V> MongoUpdate mul(AppendableIncKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Action.MUL);
    }

    static <K, X> MongoUpdate push(ArrayStatsKey<K, X> statsKey, K k, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, k, v, ArrayAction.PUSH);
    }

    static <X> MongoUpdate push(AppendableArrayKey<Void, X> statsKey, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, null, v, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <K, X> MongoUpdate push(ArrayStatsKey<K, X> statsKey, K k, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, ArrayList::new, xs, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <K, X> MongoUpdate push(ArrayStatsKey<K, X> statsKey, K k, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, supplier, xs, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <X> MongoUpdate push(AppendableArrayKey<Void, X> statsKey, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, ArrayList::new, xs, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <X> MongoUpdate push(AppendableArrayKey<Void, X> statsKey, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, supplier, xs, ArrayAction.PUSH);
    }

    static <K, X> MongoUpdate pull(ArrayStatsKey<K, X> statsKey, K k, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, k, v, ArrayAction.PULL);
    }

    static <X> MongoUpdate pull(AppendableArrayKey<Void, X> statsKey, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, null, v, ArrayAction.PULL);
    }

    @SafeVarargs
    static <K, X> MongoUpdate pull(ArrayStatsKey<K, X> statsKey, K k, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, ArrayList::new, xs, ArrayAction.PULL);
    }

    @SafeVarargs
    static <K, X> MongoUpdate pull(ArrayStatsKey<K, X> statsKey, K k, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, supplier, xs, ArrayAction.PULL);
    }

    @SafeVarargs
    static <X> MongoUpdate pull(AppendableArrayKey<Void, X> statsKey, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, ArrayList::new, xs, ArrayAction.PULL);
    }

    @SafeVarargs
    static <X> MongoUpdate pull(AppendableArrayKey<Void, X> statsKey, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, supplier, xs, ArrayAction.PULL);
    }

    static <K, X> MongoUpdate push(SetStatsKey<K, X> statsKey, K k, Set<X> v) {
        return new SetUpdateImpl<K, X>(statsKey, k, v, ArrayAction.PUSH);
    }

    static <X> MongoUpdate push(AppendableSetKey<Void, X> statsKey, Set<X> v) {
        return new SetUpdateImpl<>(statsKey, null, v, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <K, X> MongoUpdate push(SetStatsKey<K, X> statsKey, K k, X... xs) {
        return new SetUpdateImpl<>(statsKey, k, HashSet::new, xs, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <K, X> MongoUpdate push(SetStatsKey<K, X> statsKey, K k, Supplier<Set<X>> supplier, X... xs) {
        return new SetUpdateImpl<>(statsKey, k, supplier, xs, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <X> MongoUpdate push(AppendableSetKey<Void, X> statsKey, X... xs) {
        return new SetUpdateImpl<>(statsKey, null, HashSet::new, xs, ArrayAction.PUSH);
    }

    @SafeVarargs
    static <X> MongoUpdate push(AppendableSetKey<Void, X> statsKey, Supplier<Set<X>> supplier, X... xs) {
        return new SetUpdateImpl<>(statsKey, null, supplier, xs, ArrayAction.PUSH);
    }

    static <K, X> MongoUpdate pull(SetStatsKey<K, X> statsKey, K k, Set<X> v) {
        return new SetUpdateImpl<>(statsKey, k, v, ArrayAction.PULL);
    }

    static <X> MongoUpdate pull(AppendableSetKey<Void, X> statsKey, Set<X> v) {
        return new SetUpdateImpl<>(statsKey, null, v, ArrayAction.PULL);
    }

    @SafeVarargs
    static <K, X> MongoUpdate pull(SetStatsKey<K, X> statsKey, K k, X... xs) {
        return new SetUpdateImpl<>(statsKey, k, HashSet::new, xs, ArrayAction.PULL);
    }

    @SafeVarargs
    static <K, X> MongoUpdate pull(SetStatsKey<K, X> statsKey, K k, Supplier<Set<X>> supplier, X... xs) {
        return new SetUpdateImpl<>(statsKey, k, supplier, xs, ArrayAction.PULL);
    }

    @SafeVarargs
    static <X> MongoUpdate pull(AppendableSetKey<Void, X> statsKey, X... xs) {
        return new SetUpdateImpl<>(statsKey, null, HashSet::new, xs, ArrayAction.PULL);
    }

    @SafeVarargs
    static <X> MongoUpdate pull(AppendableSetKey<Void, X> statsKey, Supplier<Set<X>> supplier, X... xs) {
        return new SetUpdateImpl<>(statsKey, null, supplier, xs, ArrayAction.PULL);
    }
}
