package net.kitpvp.stats.mongodb.model;

import com.mongodb.assertions.Assertions;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.lang.Nullable;
import net.kitpvp.stats.Key;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonType;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.mongodb.assertions.Assertions.notNull;

public final class Filters {

    public static <TItem> Bson eq(TItem value) {
        return com.mongodb.client.model.Filters.eq(value);
    }

    public static <K, TItem> Bson eq(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.eq(buildKey(statKey, key), value);
    }

    public static <K, TItem> Bson ne(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Filters.ne(buildKey(statKey, key), value);
    }

    public static <K, TItem> Bson gt(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.gt(buildKey(statKey, key), value);
    }

    public static <K, TItem> Bson lt(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.lt(buildKey(statKey, key), value);
    }

    public static <K, TItem> Bson gte(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.gte(buildKey(statKey, key), value);
    }

    public static <K, TItem> Bson lte(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Filters.lte(buildKey(statKey, key), value);
    }

    @SafeVarargs
    public static <K, TItem> Bson in(Key<K> statKey, K key, TItem... values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(buildKey(statKey, key), values);
    }

    public static <K, TItem> Bson in(Key<K> statKey, K key, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.in(buildKey(statKey, key), values);
    }

    @SafeVarargs
    public static <K, TItem> Bson nin(@NotNull Key<K> statKey, K key, TItem... values) {
        notNull("values", values);
        return nin(statKey, key, Arrays.asList(values));
    }

    public static <K, TItem> Bson nin(@NotNull Key<K> statKey, K key, Iterable<TItem> values) {
        notNull("values", values);
        return com.mongodb.client.model.Filters.nin(buildKey(statKey, key), values);
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

    public static <K> Bson exists(@NotNull Key<K> statKey, K key) {
        return exists(statKey, key, true);
    }

    public static <K> Bson exists(@NotNull Key<K> statKey, K key, boolean exists) {
        return com.mongodb.client.model.Filters.exists(buildKey(statKey, key), exists);
    }

    public static <K> Bson type(@NotNull Key<K> statKey, K key, BsonType type) {
        return com.mongodb.client.model.Filters.type(buildKey(statKey, key), type);
    }

    public static <K> Bson type(@NotNull Key<K> statKey, K key, String type) {
        return com.mongodb.client.model.Filters.type(buildKey(statKey, key), type);
    }

    public static <K> Bson mod(@NotNull Key<K> statKey, K key, long divisor, long remainder) {
        return com.mongodb.client.model.Filters.mod(buildKey(statKey, key), divisor, remainder);
    }

    public static <K> Bson regex(@NotNull Key<K> statKey, K key, String pattern) {
        return regex(statKey, key, pattern, (String)null);
    }

    public static <K> Bson regex(@NotNull Key<K> statKey, K key, String pattern, @Nullable String options) {
        Assertions.notNull("pattern", pattern);
        return com.mongodb.client.model.Filters.regex(buildKey(statKey, key), pattern, options);
    }

    public static <K> Bson regex(@NotNull Key<K> statKey, K key, Pattern pattern) {
        Assertions.notNull("pattern", pattern);
        return com.mongodb.client.model.Filters.regex(buildKey(statKey, key), pattern);
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
    public static <K, TItem> Bson all(@NotNull Key<K> statKey, K key, TItem... values) {
        return all(statKey, key, Arrays.asList(values));
    }

    public static <K, TItem> Bson all(@NotNull Key<K> statKey, K key, Iterable<TItem> values) {
        return com.mongodb.client.model.Filters.all(buildKey(statKey, key), values);
    }

    public static <K> Bson elemMatch(final @NotNull Key<K> statKey, K key, final Bson filter) {
        return new Bson() {
            private final String fieldName = buildKey(statKey, key);

            public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
                return new BsonDocument(fieldName, new BsonDocument("$elemMatch", filter.toBsonDocument(documentClass, codecRegistry)));
            }
        };
    }

    public static <K> Bson size(@NotNull Key<K> statKey, K key, int size) {
        return com.mongodb.client.model.Filters.size(buildKey(statKey, key), size);
    }

    public static <K> Bson bitsAllClear(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllClear(buildKey(statKey, key), bitmask);
    }

    public static <K> Bson bitsAllSet(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAllSet(buildKey(statKey, key), bitmask);
    }

    public static <K> Bson bitsAnyClear(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnyClear(buildKey(statKey, key), bitmask);
    }

    public static <K> Bson bitsAnySet(@NotNull Key<K> statKey, K key, long bitmask) {
        return com.mongodb.client.model.Filters.bitsAnySet(buildKey(statKey, key), bitmask);
    }

    private static <K> String buildKey(Key<K> statKey, K key) {
        return notNull("statKey", statKey).key(notNull("key", key));
    }

    private Filters() {}
}
