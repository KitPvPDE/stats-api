package net.kitpvp.stats.mongodb.model;

import com.mongodb.MongoNamespace;
import com.mongodb.client.model.*;
import com.mongodb.lang.Nullable;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

public final class Aggregates {

    public static BsonField field(String fieldName, Bson field) {
        return new BsonField(fieldName, field);
    }

    public static Bson addFields(Field<?>... fields) {
        return com.mongodb.client.model.Aggregates.addFields(Arrays.asList(fields));
    }

    public static Bson addFields(List<Field<?>> fields) {
        return com.mongodb.client.model.Aggregates.addFields(fields);
    }

    public static <TExpression, Boundary> Bson bucket(TExpression groupBy, List<Boundary> boundaries) {
        return com.mongodb.client.model.Aggregates.bucket(groupBy, boundaries);
    }

    public static <TExpression, TBoundary> Bson bucket(TExpression groupBy, List<TBoundary> boundaries, BucketOptions options) {
        return com.mongodb.client.model.Aggregates.bucket(groupBy, boundaries, options);
    }

    public static <TExpression> Bson bucketAuto(TExpression groupBy, int buckets) {
        return com.mongodb.client.model.Aggregates.bucketAuto(groupBy, buckets);
    }

    public static <TExpression> Bson bucketAuto(TExpression groupBy, int buckets, BucketAutoOptions options) {
        return com.mongodb.client.model.Aggregates.bucketAuto(groupBy, buckets, options);
    }

    public static Bson count() {
        return com.mongodb.client.model.Aggregates.count();
    }

    public static Bson count(VoidKey voidKey) {
        return count(voidKey.key());
    }

    public static <T> Bson count(Key<T> key, T t) {
        return count(key.key(t));
    }

    public static Bson count(String field) {
        return com.mongodb.client.model.Aggregates.count(field);
    }

    public static Bson match(Bson filter) {
        return com.mongodb.client.model.Aggregates.match(filter);
    }

    public static Bson project(Bson projection) {
        return com.mongodb.client.model.Aggregates.project(projection);
    }

    public static Bson sort(Bson sort) {
        return com.mongodb.client.model.Aggregates.sort(sort);
    }

    public static <TExpression> Bson sortByCount(TExpression filter) {
        return com.mongodb.client.model.Aggregates.sortByCount(filter);
    }

    public static Bson skip(int skip) {
        return com.mongodb.client.model.Aggregates.skip(skip);
    }

    public static Bson limit(int limit) {
        return com.mongodb.client.model.Aggregates.limit(limit);
    }

    public static Bson lookup(String from, String localField, String foreignField, String as) {
        return com.mongodb.client.model.Aggregates.lookup(from, localField, foreignField, as);
    }

    public static Bson lookup(String from, List<? extends Bson> pipeline, String as) {
        return com.mongodb.client.model.Aggregates.lookup(from, pipeline, as);
    }

    public static <TExpression> Bson lookup(String from, @Nullable List<Variable<TExpression>> let, List<? extends Bson> pipeline, String as) {
        return com.mongodb.client.model.Aggregates.lookup(from, let, pipeline, as);
    }

    public static Bson facet(List<Facet> facets) {
        return com.mongodb.client.model.Aggregates.facet(facets);
    }

    public static Bson facet(Facet... facets) {
        return com.mongodb.client.model.Aggregates.facet(facets);
    }

    public static <TExpression> Bson graphLookup(String from, TExpression startWith, String connectFromField, String connectToField, String as) {
        return com.mongodb.client.model.Aggregates.graphLookup(from, startWith, connectFromField, connectToField, as);
    }

    public static <TExpression> Bson graphLookup(String from, TExpression startWith, String connectFromField, String connectToField, String as, GraphLookupOptions options) {
        return Aggregates.graphLookup(from, startWith, connectFromField, connectToField, as, options);
    }

    public static <TExpression> Bson group(@Nullable TExpression id, BsonField... fieldAccumulators) {
        return com.mongodb.client.model.Aggregates.group(id, fieldAccumulators);
    }

    public static <TExpression> Bson group(@Nullable TExpression id, List<BsonField> fieldAccumulators) {
        return com.mongodb.client.model.Aggregates.group(id, fieldAccumulators);
    }

    public static Bson unwind(String fieldName) {
        return com.mongodb.client.model.Aggregates.unwind(fieldName);
    }

    public static Bson unwind(VoidKey voidKey) {
        return unwind(voidKey.key());
    }

    public static <T> Bson unwind(Key<T> key, T t) {
        return unwind(key.key(t));
    }

    public static Bson unwind(String fieldName, UnwindOptions unwindOptions) {
        return com.mongodb.client.model.Aggregates.unwind(fieldName, unwindOptions);
    }

    public static Bson unwind(VoidKey voidKey, UnwindOptions unwindOptions) {
        return unwind(voidKey.key(), unwindOptions);
    }

    public static <T> Bson unwind(Key<T> key, T t, UnwindOptions unwindOptions) {
        return unwind(key.key(t), unwindOptions);
    }

    public static Bson out(String collectionName) {
        return com.mongodb.client.model.Aggregates.out(collectionName);
    }

    public static Bson merge(String collectionName) {
        return com.mongodb.client.model.Aggregates.merge(collectionName);
    }

    public static Bson merge(MongoNamespace namespace) {
        return com.mongodb.client.model.Aggregates.merge(namespace);
    }

    public static Bson merge(String collectionName, MergeOptions options) {
        return com.mongodb.client.model.Aggregates.merge(collectionName, options);
    }

    public static Bson merge(MongoNamespace namespace, MergeOptions options) {
        return com.mongodb.client.model.Aggregates.merge(namespace, options);
    }

    public static <TExpression> Bson replaceRoot(TExpression value) {
        return com.mongodb.client.model.Aggregates.replaceRoot(value);
    }

    public static <TExpression> Bson replaceWith(TExpression value) {
        return com.mongodb.client.model.Aggregates.replaceWith(value);
    }

    public static Bson sample(int size) {
        return com.mongodb.client.model.Aggregates.sample(size);
    }
}
