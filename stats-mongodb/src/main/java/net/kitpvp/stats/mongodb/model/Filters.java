package net.kitpvp.stats.mongodb.model;

import com.mongodb.assertions.Assertions;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.lang.Nullable;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import net.kitpvp.stats.mongodb.Mongo;
import net.kitpvp.stats.mongodb.MongoUuidRepresentation;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonType;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.mongodb.assertions.Assertions.notNull;

public final class Filters {

    public static <TItem> Bson eq(TItem value) {
        if(value instanceof UUID && Mongo.getUuidRepresentation() == MongoUuidRepresentation.STRING) {
            return com.mongodb.client.model.Filters.eq(value.toString());
        }
        return com.mongodb.client.model.Filters.eq(value);
    }

    public static <TItem> Bson eq(String fieldName, TItem value) {
        return com.mongodb.client.model.Filters.eq(fieldName, value);
    }

    public static <TItem> Bson eq(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Filters.eq(statKey.key(), value);
    }

    public static <K, TItem> Bson eq(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.eq(statKey.key(key), value);
    }

    public static <TItem> Bson ne(String fieldName, @Nullable TItem value) {
        return com.mongodb.client.model.Filters.ne(fieldName, value);
    }

    public static <TItem> Bson ne(VoidKey statKey, @Nullable TItem value) {
        return com.mongodb.client.model.Filters.ne(statKey.key(), value);
    }

    public static <K, TItem> Bson ne(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Filters.ne(statKey.key(key), value);
    }

    public static <TItem> Bson gt(String fieldName, TItem value) {
        return com.mongodb.client.model.Filters.gt(fieldName, value);
    }

    public static <TItem> Bson gt(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Filters.gt(statKey.key(), value);
    }

    public static <K, TItem> Bson gt(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.gt(statKey.key(key), value);
    }

    public static <TItem> Bson lt(String fieldName, TItem value) {
        return com.mongodb.client.model.Filters.lt(fieldName, value);
    }

    public static <TItem> Bson lt(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Filters.lt(statKey.key(), value);
    }

    public static <K, TItem> Bson lt(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.lt(statKey.key(key), value);
    }

    public static <TItem> Bson gte(String fieldName, TItem value) {
        return com.mongodb.client.model.Filters.gte(fieldName, value);
    }

    public static <TItem> Bson gte(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Filters.gte(statKey.key(), value);
    }

    public static <K, TItem> Bson gte(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.gte(statKey.key(key), value);
    }

    public static <TItem> Bson lte(String fieldName, TItem value) {
        return com.mongodb.client.model.Filters.lte(fieldName, value);
    }

    public static <TItem> Bson lte(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Filters.lte(statKey.key(), value);
    }

    public static <K, TItem> Bson lte(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.lte(statKey.key(key), value);
    }

    @SafeVarargs
    public static <TItem> Bson in(String fieldName, TItem... values) {
        return com.mongodb.client.model.Filters.in(fieldName, values);
    }

    @SafeVarargs
    public static <TItem> Bson in(VoidKey statKey, TItem... values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(statKey.key(), values);
    }

    @SafeVarargs
    public static <K, TItem> Bson in(Key<K> statKey, K key, TItem... values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(statKey.key(key), values);
    }

    public static <TItem> Bson in(String fieldName, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(fieldName, values);
    }

    public static <TItem> Bson in(VoidKey statKey, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(statKey.key(), values);
    }

    public static <K, TItem> Bson in(Key<K> statKey, K key, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(statKey.key(key), values);
    }

    @SafeVarargs
    public static <TItem> Bson nin(String fieldName, TItem... values) {
        return com.mongodb.client.model.Filters.nin(fieldName, values);
    }

    @SafeVarargs
    public static <TItem> Bson nin(VoidKey statKey, TItem... values) {
        notNull("values", values);
        return nin(statKey, Arrays.asList(values));
    }

    @SafeVarargs
    public static <K, TItem> Bson nin(@NotNull Key<K> statKey, K key, TItem... values) {
        notNull("values", values);
        return nin(statKey, key, Arrays.asList(values));
    }

    public static <TItem> Bson nin(String fieldName, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.nin(fieldName, values);
    }

    public static <TItem> Bson nin(VoidKey statKey, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.nin(statKey.key(), values);
    }

    public static <K, TItem> Bson nin(@NotNull Key<K> statKey, K key, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.nin(statKey.key(key), values);
    }

    public static Bson and(Iterable<Bson> filters) {
        return com.mongodb.client.model.Filters.and(filters);
    }

    public static Bson and(Bson... filters) {
        return and(Arrays.asList(filters));
    }

    public static Bson or(Iterable<Bson> filters) {
        return com.mongodb.client.model.Filters.or(filters);
    }

    public static Bson or(Bson... filters) {
        return or(Arrays.asList(filters));
    }

    public static Bson not(Bson filter) {
        return com.mongodb.client.model.Filters.not(filter);
    }

    public static Bson nor(Bson... filters) {
        return nor(Arrays.asList(filters));
    }

    public static Bson nor(Iterable<Bson> filters) {
        return com.mongodb.client.model.Filters.nor(filters);
    }

    public static Bson exists(String fieldName) {
        return com.mongodb.client.model.Filters.exists(fieldName);
    }

    public static Bson exists(VoidKey statKey) {
        return exists(statKey, false);
    }

    public static <K> Bson exists(@NotNull Key<K> statKey, K key) {
        return exists(statKey, key, true);
    }

    public static Bson exists(String fieldName, boolean exists) {
        return com.mongodb.client.model.Filters.exists(fieldName, exists);
    }

    public static Bson exists(VoidKey statKey, boolean exists) {
        return com.mongodb.client.model.Filters.exists(statKey.key(), exists);
    }

    public static <K> Bson exists(@NotNull Key<K> statKey, K key, boolean exists) {
        return com.mongodb.client.model.Filters.exists(statKey.key(key), exists);
    }

    public static Bson type(String fieldName, BsonType type) {
        return com.mongodb.client.model.Filters.type(fieldName, type);
    }

    public static Bson type(VoidKey statKey, BsonType type) {
        return com.mongodb.client.model.Filters.type(statKey.key(), type);
    }

    public static <K> Bson type(@NotNull Key<K> statKey, K key, BsonType type) {
        return com.mongodb.client.model.Filters.type(statKey.key(key), type);
    }

    public static Bson type(String fieldName, String type) {
        return com.mongodb.client.model.Filters.type(fieldName, type);
    }

    public static Bson type(VoidKey statKey, String type) {
        return com.mongodb.client.model.Filters.type(statKey.key(), type);
    }

    public static <K> Bson type(@NotNull Key<K> statKey, K key, String type) {
        return com.mongodb.client.model.Filters.type(statKey.key(key), type);
    }

    public static Bson mod(String fieldName, long divisor, long remainder) {
        return com.mongodb.client.model.Filters.mod(fieldName, divisor, remainder);
    }

    public static Bson mod(VoidKey statKey, long divisor, long remainder) {
        return com.mongodb.client.model.Filters.mod(statKey.key(), divisor, remainder);
    }

    public static <K> Bson mod(@NotNull Key<K> statKey, K key, long divisor, long remainder) {
        return com.mongodb.client.model.Filters.mod(statKey.key(key), divisor, remainder);
    }

    public static Bson regex(String fieldName, String pattern) {
        return com.mongodb.client.model.Filters.regex(fieldName, pattern);
    }

    public static Bson regex(VoidKey statKey, String pattern) {
        return regex(statKey, pattern, null);
    }

    public static <K> Bson regex(@NotNull Key<K> statKey, K key, String pattern) {
        return regex(statKey, key, pattern, null);
    }

    public static Bson regex(String fieldName, String pattern, @Nullable String options) {
        return com.mongodb.client.model.Filters.regex(fieldName, pattern, options);
    }

    public static Bson regex(VoidKey statKey, String pattern, @Nullable String options) {
        notNull("pattern", pattern);
        return com.mongodb.client.model.Filters.regex(statKey.key(), pattern, options);
    }

    public static <K> Bson regex(@NotNull Key<K> statKey, K key, String pattern, @Nullable String options) {
        Assertions.notNull("pattern", pattern);
        return com.mongodb.client.model.Filters.regex(statKey.key(key), pattern, options);
    }

    public static Bson regex(String fieldName, Pattern pattern) {
        return com.mongodb.client.model.Filters.regex(fieldName, pattern);
    }

    public static Bson regex(VoidKey statKey, Pattern pattern) {
        notNull("pattern", pattern);
        return com.mongodb.client.model.Filters.regex(statKey.key(), pattern);
    }

    public static <K> Bson regex(@NotNull Key<K> statKey, K key, Pattern pattern) {
        Assertions.notNull("pattern", pattern);
        return com.mongodb.client.model.Filters.regex(statKey.key(key), pattern);
    }

    public static Bson text(String search) {
        Assertions.notNull("search", search);
        return text(search, new TextSearchOptions());
    }

    public static Bson text(String search, TextSearchOptions textSearchOptions) {
        Assertions.notNull("search", search);
        Assertions.notNull("textSearchOptions", textSearchOptions);
        return com.mongodb.client.model.Filters.text(search, textSearchOptions);
    }

    public static Bson where(String javaScriptExpression) {
        Assertions.notNull("javaScriptExpression", javaScriptExpression);
        return new BsonDocument("$where", new BsonString(javaScriptExpression));
    }

    public static <TExpression> Bson expr(TExpression expression) {
        return com.mongodb.client.model.Filters.expr(expression);
    }

    @SafeVarargs
    public static <TItem> Bson all(String fieldName, TItem... values) {
        return com.mongodb.client.model.Filters.all(fieldName, values);
    }

    @SafeVarargs
    public static <TItem> Bson all(VoidKey statKey, TItem... values) {
        return all(statKey, Arrays.asList(values));
    }

    @SafeVarargs
    public static <K, TItem> Bson all(Key<K> statKey, K key, TItem... values) {
        return all(statKey, key, Arrays.asList(values));
    }

    public static <TItem> Bson all(String fieldName, Iterable<TItem> values) {
        return com.mongodb.client.model.Filters.all(fieldName, values);
    }

    public static <TItem> Bson all(VoidKey statKey, Iterable<TItem> values) {
        return com.mongodb.client.model.Filters.all(statKey.key(), values);
    }

    public static <K, TItem> Bson all(Key<K> statKey, K key, Iterable<TItem> values) {
        return com.mongodb.client.model.Filters.all(statKey.key(key), values);
    }

    public static Bson elemMatch(final String fieldName, final Bson filter) {
        return com.mongodb.client.model.Filters.elemMatch(fieldName, filter);
    }

    public static Bson elemMatch(final VoidKey statKey, final Bson filter) {
        return new Bson() {
            private final String fieldName = statKey.key();

            public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
                return new BsonDocument(fieldName, new BsonDocument("$elemMatch", filter.toBsonDocument(documentClass, codecRegistry)));
            }
        };
    }

    public static <K> Bson elemMatch(final @NotNull Key<K> statKey, K key, final Bson filter) {
        return new Bson() {
            private final String fieldName = statKey.key(key);

            public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
                return new BsonDocument(fieldName, new BsonDocument("$elemMatch", filter.toBsonDocument(documentClass, codecRegistry)));
            }
        };
    }

    public static Bson size(String fieldName, int size) {
        return com.mongodb.client.model.Filters.size(fieldName, size);
    }

    public static Bson size(VoidKey statKey, int size) {
        return com.mongodb.client.model.Filters.size(statKey.key(), size);
    }

    public static <K> Bson size(@NotNull Key<K> statKey, K key, int size) {
        return com.mongodb.client.model.Filters.size(statKey.key(key), size);
    }

    public static Bson bitsAllClear(String fieldName, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllClear(fieldName, bitmask);
    }
    
    public static Bson bitsAllClear(VoidKey statKey, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllClear(statKey.key(), bitmask);
    }

    public static <K> Bson bitsAllClear(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllClear(statKey.key(key), bitmask);
    }

    public static Bson bitsAllSet(String fieldName, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllSet(fieldName, bitmask);
    }
    
    public static Bson bitsAllSet(VoidKey statKey, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllSet(statKey.key(), bitmask);
    }

    public static <K> Bson bitsAllSet(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllSet(statKey.key(key), bitmask);
    }

    public static Bson bitsAnyClear(String fieldName, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnyClear(fieldName, bitmask);
    }
    
    public static Bson bitsAnyClear(VoidKey statKey, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnyClear(statKey.key(), bitmask);
    }

    public static <K> Bson bitsAnyClear(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnyClear(statKey.key(key), bitmask);
    }

    public static Bson bitsAnySet(String fieldName, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnySet(fieldName, bitmask);
    }
    
    public static Bson bitsAnySet(VoidKey statKey, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnySet(statKey.key(), bitmask);
    }

    public static <K> Bson bitsAnySet(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnySet(statKey.key(key), bitmask);
    }

    // mapping functions

    public static <UItem, TItem> Bson eq(Function<TItem, UItem> function, TItem value) {
        return eq(function.apply(value));
    }

    public static <UItem, TItem> Bson eq(VoidKey statKey, Function<TItem, UItem> function, TItem value) {
        return eq(statKey, function.apply(value));
    }

    public static <K, UItem, TItem> Bson eq(Key<K> statKey, K key, Function<TItem, UItem> function, TItem value) {
        return eq(statKey, key, function.apply(value));
    }

    public static <UItem, TItem> Bson ne(VoidKey statKey, Function<TItem, UItem> function, @Nullable TItem value) {
        return ne(statKey, function.apply(value));
    }

    public static <K, UItem, TItem> Bson ne(Key<K> statKey, K key, Function<TItem, UItem> function, @Nullable TItem value) {
        return ne(statKey, key, function.apply(value));
    }

    public static <UItem, TItem> Bson gt(VoidKey statKey, Function<TItem, UItem> function, TItem value) {
        return gt(statKey, function.apply(value));
    }

    public static <K, UItem, TItem> Bson gt(Key<K> statKey, K key, Function<TItem, UItem> function, TItem value) {
        return gt(statKey, key, function.apply(value));
    }

    public static <UItem, TItem> Bson lt(VoidKey statKey, Function<TItem, UItem> function, TItem value) {
        return lt(statKey, function.apply(value));
    }

    public static <K, UItem, TItem> Bson lt(Key<K> statKey, K key, Function<TItem, UItem> function, TItem value) {
        return lt(statKey, key, function.apply(value));
    }

    public static <UItem, TItem> Bson gte(VoidKey statKey, Function<TItem, UItem> function, TItem value) {
        return gte(statKey, function.apply(value));
    }

    public static <K, UItem, TItem> Bson gte(Key<K> statKey, K key, Function<TItem, UItem> function, TItem value) {
        return gte(statKey, key, function.apply(value));
    }

    public static <UItem, TItem> Bson lte(VoidKey statKey, Function<TItem, UItem> function, TItem value) {
        return lte(statKey, function.apply(value));
    }

    public static <K, UItem, TItem> Bson lte(Key<K> statKey, K key, Function<TItem, UItem> function, TItem value) {
        return lte(statKey, key, function.apply(value));
    }

    @SafeVarargs
    public static <UItem, TItem> Bson in(VoidKey statKey, Function<TItem, UItem> function, TItem... values) {
        notNull("values", values);
        return in(statKey, function, Arrays.asList(values));
    }

    @SafeVarargs
    public static <K, UItem, TItem> Bson in(Key<K> statKey, K key, Function<TItem, UItem> function, TItem... values) {
        notNull("values", values);
        return in(statKey, key, function, Arrays.asList(values));
    }

    public static <UItem, TItem> Bson in(VoidKey statKey, Function<TItem, UItem> function, Iterable<TItem> values) {
        notNull("values", values);
        return in(statKey, new MappedIterable<>(values, function));
    }

    public static <K, UItem, TItem> Bson in(Key<K> statKey, K key, Function<TItem, UItem> function, Iterable<TItem> values) {
        notNull("values", values);
        return in(statKey, key, new MappedIterable<>(values, function));
    }

    @SafeVarargs
    public static <UItem, TItem> Bson nin(VoidKey statKey, Function<TItem, UItem> function, TItem... values) {
        notNull("values", values);
        return nin(statKey, function, Arrays.asList(values));
    }

    @SafeVarargs
    public static <K, UItem, TItem> Bson nin(@NotNull Key<K> statKey, K key, Function<TItem, UItem> function, TItem... values) {
        notNull("values", values);
        return nin(statKey, key, function, Arrays.asList(values));
    }

    public static <UItem, TItem> Bson nin(VoidKey statKey, Function<TItem, UItem> function, Iterable<TItem> values) {
        notNull("values", values);
        return nin(statKey, new MappedIterable<>(values, function));
    }

    public static <K, UItem, TItem> Bson nin(@NotNull Key<K> statKey, K key, Function<TItem, UItem> function, Iterable<TItem> values) {
        notNull("values", values);
        return nin(statKey, key, new MappedIterable<>(values, function));
    }

    @SafeVarargs
    public static <UItem, TItem> Bson all(VoidKey statKey, Function<TItem, UItem> function, TItem... values) {
        return all(statKey, function, Arrays.asList(values));
    }

    @SafeVarargs
    public static <K, UItem, TItem> Bson all(Key<K> statKey, K key, Function<TItem, UItem> function, TItem... values) {
        return all(statKey, key, function, Arrays.asList(values));
    }

    public static <UItem, TItem> Bson all(VoidKey statKey, Function<TItem, UItem> function, Iterable<TItem> values) {
        return all(statKey, new MappedIterable<>(values, function));
    }

    public static <K, UItem, TItem> Bson all(Key<K> statKey, K key, Function<TItem, UItem> function, Iterable<TItem> values) {
        return all(statKey, key, new MappedIterable<>(values, function));
    }

    @RequiredArgsConstructor
    private static class MappedIterable<UItem, KItem> implements Iterable<UItem> {

        private final Iterable<KItem> iterable;
        private final Function<KItem, UItem> function;

        @NotNull
        @Override
        public Iterator<UItem> iterator() {
            return new Iterator<UItem>() {
                private final Iterator<KItem> iterator = MappedIterable.this.iterable.iterator();

                @Override
                public boolean hasNext() {
                    return this.iterator.hasNext();
                }

                @Override
                public UItem next() {
                    return function.apply(this.iterator.next());
                }
            };
        }

        @Override
        public void forEach(Consumer<? super UItem> action) {
            this.iterable.forEach(kItem -> action.accept(this.function.apply(kItem)));
        }

        @Override
        public Spliterator<UItem> spliterator() {
            return Iterable.super.spliterator();
        }
    }
}
