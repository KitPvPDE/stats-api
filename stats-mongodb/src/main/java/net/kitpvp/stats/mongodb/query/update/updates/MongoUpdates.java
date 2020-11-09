package net.kitpvp.stats.mongodb.query.update.updates;

import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.mongodb.query.update.ArrayOperator;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import net.kitpvp.stats.mongodb.query.update.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface MongoUpdates {

    static <K, V> MongoUpdate set(AppendableKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Operator.SET);
    }

    static <K, V> MongoUpdate unset(AppendableKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Operator.UNSET);
    }

    static <K, V> MongoUpdate inc(AppendableIncKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Operator.INC);
    }

    static <K> MongoUpdate inc(AppendableIncKey<K, Integer> statsKey, K k, int v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Operator.INC);
    }

    static <K, V> MongoUpdate dec(AppendableIncKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Operator.DEC);
    }

    static <K, V> MongoUpdate mul(AppendableIncKey<K, V> statsKey, K k, V v) {
        return new MongoUpdateImpl<>(statsKey, k, v, Operator.MUL);
    }

    static <K, X> MongoUpdate push(ArrayStatsKey<K, X> statsKey, K k, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, k, v, ArrayOperator.PUSH);
    }

    static <X> MongoUpdate push(AppendableArrayKey<Void, X> statsKey, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, null, v, ArrayOperator.PUSH);
    }

    @SafeVarargs
    static <K, X> MongoUpdate push(ArrayStatsKey<K, X> statsKey, K k, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, ArrayList::new, xs, ArrayOperator.PUSH);
    }

    @SafeVarargs
    static <K, X> MongoUpdate push(ArrayStatsKey<K, X> statsKey, K k, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, supplier, xs, ArrayOperator.PUSH);
    }

    @SafeVarargs
    static <X> MongoUpdate push(AppendableArrayKey<Void, X> statsKey, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, ArrayList::new, xs, ArrayOperator.PUSH);
    }

    @SafeVarargs
    static <X> MongoUpdate push(AppendableArrayKey<Void, X> statsKey, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, supplier, xs, ArrayOperator.PUSH);
    }

    static <K, X> MongoUpdate pull(ArrayStatsKey<K, X> statsKey, K k, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, k, v, ArrayOperator.PULL);
    }

    static <X> MongoUpdate pull(AppendableArrayKey<Void, X> statsKey, List<X> v) {
        return new ArrayUpdateImpl<>(statsKey, null, v, ArrayOperator.PULL);
    }

    @SafeVarargs
    static <K, X> MongoUpdate pull(ArrayStatsKey<K, X> statsKey, K k, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, ArrayList::new, xs, ArrayOperator.PULL);
    }

    @SafeVarargs
    static <K, X> MongoUpdate pull(ArrayStatsKey<K, X> statsKey, K k, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, k, supplier, xs, ArrayOperator.PULL);
    }

    @SafeVarargs
    static <X> MongoUpdate pull(AppendableArrayKey<Void, X> statsKey, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, ArrayList::new, xs, ArrayOperator.PULL);
    }

    @SafeVarargs
    static <X> MongoUpdate pull(AppendableArrayKey<Void, X> statsKey, Supplier<List<X>> supplier, X... xs) {
        return new ArrayUpdateImpl<>(statsKey, null, supplier, xs, ArrayOperator.PULL);
    }

}
