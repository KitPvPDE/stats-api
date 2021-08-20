package net.kitpvp.stats.mongodb.model;

import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Indexes {

    public static Bson ascending(String... fieldNames) {
        return com.mongodb.client.model.Indexes.ascending(fieldNames);
    }

    public static Bson ascendingByKeys(VoidKey... keys) {
        return ascendingByKeys(Arrays.asList(keys));
    }

    public static Bson ascendingByKeys(List<VoidKey> keys) {
        return ascending(keys.stream().map(VoidKey::key).collect(Collectors.toList()));
    }

    public static <T> Bson ascendingByKey(Key<T> key, T t) {
        return ascending(key.key(t));
    }

    public static Bson ascending(List<String> fieldNames) {
        return com.mongodb.client.model.Indexes.ascending(fieldNames);
    }

    public static Bson descending(String... fieldNames) {
        return com.mongodb.client.model.Indexes.descending(fieldNames);
    }

    public static Bson descending(List<String> fieldNames) {
        return com.mongodb.client.model.Indexes.descending(fieldNames);
    }

    public static Bson descendingByKeys(VoidKey... keys) {
        return descendingByKeys(Arrays.asList(keys));
    }

    public static Bson descendingByKeys(List<VoidKey> keys) {
        return descending(keys.stream().map(VoidKey::key).collect(Collectors.toList()));
    }

    public static <T> Bson descendingByKey(Key<T> key, T t) {
        return descending(key.key(t));
    }

    public static Bson text(String fieldName) {
        return com.mongodb.client.model.Indexes.text(fieldName);
    }

    public static Bson text(VoidKey voidKey) {
        return text(voidKey.key());
    }

    public static <T> Bson text(Key<T> key, T t) {
        return text(key.key(t));
    }

    public static Bson text() {
        return com.mongodb.client.model.Indexes.text();
    }

    public static Bson hashed(String fieldName) {
        return com.mongodb.client.model.Indexes.hashed(fieldName);
    }

    public static Bson hashed(VoidKey voidKey) {
        return hashed(voidKey.key());
    }

    public static <T> Bson hashed(Key<T> key, T t) {
        return hashed(key.key(t));
    }

    public static Bson compoundIndex(Bson... indexes) {
        return com.mongodb.client.model.Indexes.compoundIndex(indexes);
    }

    public static Bson compoundIndex(List<? extends Bson> indexes) {
        return com.mongodb.client.model.Indexes.compoundIndex(indexes);
    }
}
