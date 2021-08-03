package net.kitpvp.stats.mongodb.model;

import com.mongodb.assertions.Assertions;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Projections {

    public static <TExpression> Bson computed(VoidKey voidKey, TExpression expression) {
        return com.mongodb.client.model.Projections.computed(voidKey.key(), expression);
    }

    public static <TExpression, TKey> Bson computed(Key<TKey> key, TKey tKey, TExpression expression) {
        return com.mongodb.client.model.Projections.computed(key.key(tKey), expression);
    }

    public static Bson include(VoidKey... fieldNames) {
        return com.mongodb.client.model.Projections.include(Stream.of(fieldNames).map(VoidKey::key).collect(Collectors.toList()));
    }

    public static <TKey> Bson include(Key<TKey> key, TKey tKey) {
        return com.mongodb.client.model.Projections.include(key.key(tKey));
    }

    public static Bson include(List<VoidKey> fieldNames) {
        return com.mongodb.client.model.Projections.include(fieldNames.stream().map(VoidKey::key).collect(Collectors.toList()));
    }

    public static Bson exclude(VoidKey... fieldNames) {
        return com.mongodb.client.model.Projections.exclude(Stream.of(fieldNames).map(VoidKey::key).collect(Collectors.toList()));
    }

    public static <TKey> Bson exclude(Key<TKey> key, TKey tKey) {
        return com.mongodb.client.model.Projections.exclude(key.key(tKey));
    }

    public static Bson exclude(List<VoidKey> fieldNames) {
        return com.mongodb.client.model.Projections.exclude(fieldNames.stream().map(VoidKey::key).collect(Collectors.toList()));
    }

    public static Bson excludeId() {
        return com.mongodb.client.model.Projections.excludeId();
    }

    public static <TKey> Bson elemMatch(Key<TKey> key, TKey tKey) {
        return com.mongodb.client.model.Projections.elemMatch(key.key(tKey));
    }

    public static Bson elemMatch(VoidKey voidKey) {
        return com.mongodb.client.model.Projections.elemMatch(voidKey.key());
    }

    public static <TKey> Bson elemMatch(Key<TKey> key, TKey tKey, Bson filter) {
        return com.mongodb.client.model.Projections.elemMatch(key.key(tKey), filter);
    }

    public static Bson elemMatch(VoidKey fieldName, Bson filter) {
        return com.mongodb.client.model.Projections.elemMatch(fieldName.key(), filter);
    }

    public static <TKey> Bson metaTextScore(Key<TKey> key, TKey tKey) {
        return com.mongodb.client.model.Projections.metaTextScore(key.key(tKey));
    }

    public static Bson metaTextScore(VoidKey fieldName) {
        return com.mongodb.client.model.Projections.metaTextScore(fieldName.key());
    }

    public static <TKey> Bson slice(Key<TKey> key, TKey tKey, int limit) {
        return com.mongodb.client.model.Projections.slice(key.key(tKey), limit);
    }

    public static Bson slice(VoidKey fieldName, int limit) {
        return com.mongodb.client.model.Projections.slice(fieldName.key(), limit);
    }

    public static <TKey> Bson slice(Key<TKey> key, TKey tKey, int skip, int limit) {
        return com.mongodb.client.model.Projections.slice(key.key(tKey), skip, limit);
    }

    public static Bson slice(VoidKey fieldName, int skip, int limit) {
        return com.mongodb.client.model.Projections.slice(fieldName.key(), skip, limit);
    }

    public static Bson fields(Bson... projections) {
        return com.mongodb.client.model.Projections.fields(projections);
    }

    public static Bson fields(List<? extends Bson> projections) {
        Assertions.notNull("projections", projections);
        return com.mongodb.client.model.Projections.fields(projections);
    }
}
