package net.kitpvp.stats.mongodb.model;

import com.mongodb.client.model.PushOptions;
import com.mongodb.lang.Nullable;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import net.kitpvp.stats.bson.BsonWriter;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import net.kitpvp.stats.keys.IterableKey;
import net.kitpvp.stats.keys.IterableVoidKey;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.assertions.Assertions.notNull;

public final class Updates {

    public static Bson combine(Bson... updates) {
        notNull("updates", updates);
        return combine(Arrays.asList(updates));
    }

    public static Bson combine(List<? extends Bson> updates) {
        notNull("updates", updates);
        return com.mongodb.client.model.Updates.combine(updates);
    }

    public static <TItem> Bson set(TItem item, BsonEncoder<TItem> encoder) {
        return new Document("$set", encoder.encode(item).bson());
    }

    public static <TItem> Bson[] set(IterableVoidKey statKey, @Nullable TItem value) {
        return statKey.stream().map(voidKey -> set(voidKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson set(VoidKey statKey, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.set(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] set(IterableKey<K> iterableKey, K key, @Nullable TItem value) {
        return iterableKey.stream().map(statKey -> set(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson set(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.set(buildKey(statKey, key), value);
    }

    public static <TItem> Bson[] set(IterableVoidKey statKey, @Nullable TItem value, BsonEncoder<TItem> encoder) {
        BsonWriter writer = encoder.encode(value);
        return statKey.stream().map(voidKey -> set(voidKey, writer.bson())).toArray(Bson[]::new);
    }

    public static <TItem> Bson set(VoidKey statKey, @Nullable TItem value, BsonEncoder<TItem> encoder) {
        return com.mongodb.client.model.Updates.set(statKey.key(), encoder.encode(value).bson());
    }

    public static <K, TItem> Bson[] set(IterableKey<K> iterableKey, K key, @Nullable TItem value, BsonEncoder<TItem> encoder) {
        BsonWriter writer = encoder.encode(value);
        return iterableKey.stream().map(statKey -> set(statKey, key, writer.bson())).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson set(Key<K> statKey, K key, @Nullable TItem value, BsonEncoder<TItem> encoder) {
        return com.mongodb.client.model.Updates.set(statKey.key(key), encoder.encode(value).bson());
    }

    public static Bson[] unset(IterableVoidKey iterableVoidKey) {
        return iterableVoidKey.stream().map(Updates::unset).toArray(Bson[]::new);
    }

    public static Bson unset(VoidKey statKey) {
        return com.mongodb.client.model.Updates.unset(buildKey(statKey));
    }

    public static <K> Bson[] unset(IterableKey<K> iterableKey, K key) {
        return iterableKey.stream().map(statKey -> unset(statKey, key)).toArray(Bson[]::new);
    }

    public static <K> Bson unset(Key<K> statKey, K key) {
        return com.mongodb.client.model.Updates.unset(buildKey(statKey, key));
    }

    public static Bson setOnInsert(Bson value) {
        return com.mongodb.client.model.Updates.setOnInsert(value);
    }

    public static <TItem> Bson[] setOnInsert(IterableVoidKey iterableVoidKey, @Nullable TItem value) {
        return iterableVoidKey.stream().map(statKey -> setOnInsert(statKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson setOnInsert(VoidKey statKey, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.setOnInsert(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] setOnInsert(IterableKey<K> iterableKey, K key, @Nullable TItem value) {
        return iterableKey.stream().map(statKey -> setOnInsert(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson setOnInsert(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.setOnInsert(buildKey(statKey, key), value);
    }

    public static Bson rename(VoidKey statKey, VoidKey newStatKey) {
        return com.mongodb.client.model.Updates.rename(buildKey(statKey), buildKey(newStatKey));
    }

    public static <L> Bson rename(VoidKey statKey, Key<L> newStatKey, L newKey) {
        return com.mongodb.client.model.Updates.rename(buildKey(statKey), buildKey(newStatKey, newKey));
    }

    public static <K> Bson rename(Key<K> statKey, K key, VoidKey newStatKey) {
        return com.mongodb.client.model.Updates.rename(buildKey(statKey, key), buildKey(newStatKey));
    }

    public static <K, L> Bson rename(Key<K> statKey, K key, Key<L> newStatKey, L newKey) {
        return com.mongodb.client.model.Updates.rename(buildKey(statKey, key), buildKey(newStatKey, newKey));
    }

    public static Bson[] inc(IterableVoidKey iterableVoidKey, Number number) {
        return iterableVoidKey.stream().map(statKey -> inc(statKey, number)).toArray(Bson[]::new);
    }

    public static Bson inc(VoidKey statKey, Number number) {
        notNull("number", number);
        return com.mongodb.client.model.Updates.inc(buildKey(statKey), number);
    }

    public static <K> Bson[] inc(IterableKey<K> iterableKey, K key, Number number) {
        return iterableKey.stream().map(statKey -> inc(statKey, key, number)).toArray(Bson[]::new);
    }

    public static <K> Bson inc(Key<K> statKey, K key, Number number) {
        notNull("number", number);
        return com.mongodb.client.model.Updates.inc(buildKey(statKey, key), number);
    }

    public static Bson[] mul(IterableVoidKey iterableVoidKey, Number number) {
        return iterableVoidKey.stream().map(statKey -> mul(statKey, number)).toArray(Bson[]::new);
    }

    public static Bson mul(VoidKey statKey, Number number) {
        notNull("number", number);
        return com.mongodb.client.model.Updates.mul(buildKey(statKey), number);
    }

    public static <K> Bson[] mul(IterableKey<K> iterableKey, K key, Number number) {
        return iterableKey.stream().map(statKey -> mul(statKey, key, number)).toArray(Bson[]::new);
    }

    public static <K> Bson mul(Key<K> statKey, K key, Number number) {
        notNull("number", number);
        return com.mongodb.client.model.Updates.mul(buildKey(statKey, key), number);
    }

    public static <TItem> Bson[] min(IterableVoidKey iterableVoidKey, TItem value) {
        return iterableVoidKey.stream().map(statKey -> min(statKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson min(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Updates.min(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] min(IterableKey<K> iterableKey, K key, TItem value) {
        return iterableKey.stream().map(statKey -> min(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson min(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Updates.min(buildKey(statKey, key), value);
    }

    public static <TItem> Bson[] max(IterableVoidKey iterableVoidKey, TItem value) {
        return iterableVoidKey.stream().map(statKey -> max(statKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson max(VoidKey statKey, TItem value) {
        return com.mongodb.client.model.Updates.max(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] max(IterableKey<K> iterableKey, K key, TItem value) {
        return iterableKey.stream().map(statKey -> max(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson max(Key<K> statKey, K key, TItem value) {
        return com.mongodb.client.model.Updates.max(buildKey(statKey, key), value);
    }

    public static Bson[] currentDate(IterableVoidKey iterableVoidKey) {
        return iterableVoidKey.stream().map(Updates::currentDate).toArray(Bson[]::new);
    }

    public static Bson currentDate(VoidKey statKey) {
        return com.mongodb.client.model.Updates.currentDate(buildKey(statKey));
    }

    public static <K> Bson[] currentDate(IterableKey<K> iterableKey, K key) {
        return iterableKey.stream().map(statKey -> currentDate(statKey, key)).toArray(Bson[]::new);
    }

    public static <K> Bson currentDate(Key<K> statKey, K key) {
        return com.mongodb.client.model.Updates.currentDate(buildKey(statKey, key));
    }

    public static Bson[] currentTimestamp(IterableVoidKey iterableVoidKey) {
        return iterableVoidKey.stream().map(Updates::currentTimestamp).toArray(Bson[]::new);
    }

    public static Bson currentTimestamp(VoidKey statKey) {
        return com.mongodb.client.model.Updates.currentTimestamp(buildKey(statKey));
    }

    public static <K> Bson[] currentTimestamp(IterableKey<K> iterableKey, K key) {
        return iterableKey.stream().map(statKey -> currentTimestamp(statKey, key)).toArray(Bson[]::new);
    }

    public static <K> Bson currentTimestamp(Key<K> statKey, K key) {
        return com.mongodb.client.model.Updates.currentTimestamp(buildKey(statKey, key));
    }

    public static <TItem> Bson[] addToSet(IterableVoidKey iterableVoidKey, @Nullable TItem value) {
        return iterableVoidKey.stream().map(statKey -> addToSet(statKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson addToSet(VoidKey statKey, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.addToSet(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] addToSet(IterableKey<K> iterableKey, K key, @Nullable TItem value) {
        return iterableKey.stream().map(statKey -> addToSet(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson addToSet(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.addToSet(buildKey(statKey, key), value);
    }

    public static <TItem> Bson[] addEachToSet(IterableVoidKey iterableVoidKey, List<TItem> values) {
        return iterableVoidKey.stream().map(statKey -> addEachToSet(statKey, values)).toArray(Bson[]::new);
    }

    public static <TItem> Bson addEachToSet(VoidKey statKey, List<TItem> values) {
        return com.mongodb.client.model.Updates.addEachToSet(buildKey(statKey), values);
    }

    public static <K, TItem> Bson[] addEachToSet(IterableKey<K> iterableKey, K key, List<TItem> values) {
        return iterableKey.stream().map(statKey -> addEachToSet(statKey, key, values)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson addEachToSet(Key<K> statKey, K key, List<TItem> values) {
        return com.mongodb.client.model.Updates.addEachToSet(buildKey(statKey, key), values);
    }

    public static <TItem> Bson[] push(IterableVoidKey iterableVoidKey, @Nullable TItem value) {
        return iterableVoidKey.stream().map(statKey -> push(statKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson push(VoidKey statKey, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.push(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] push(IterableKey<K> iterableKey, K key, @Nullable TItem value) {
        return iterableKey.stream().map(statKey -> push(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson push(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.push(buildKey(statKey, key), value);
    }

    public static <TItem> Bson[] pushEach(IterableVoidKey iterableVoidKey, List<TItem> values) {
        return iterableVoidKey.stream().map(statKey -> pushEach(statKey, values)).toArray(Bson[]::new);
    }

    public static <TItem> Bson pushEach(VoidKey statKey, List<TItem> values) {
        return com.mongodb.client.model.Updates.pushEach(buildKey(statKey), values);
    }

    public static <K, TItem> Bson[] pushEach(IterableKey<K> iterableKey, K key, List<TItem> values) {
        return iterableKey.stream().map(statKey -> pushEach(statKey, key, values)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson pushEach(Key<K> statKey, K key, List<TItem> values) {
        return com.mongodb.client.model.Updates.pushEach(buildKey(statKey, key), values);
    }

    public static <TItem> Bson[] pushEach(IterableVoidKey iterableVoidKey, List<TItem> values, PushOptions options) {
        return iterableVoidKey.stream().map(statKey -> pushEach(statKey, values, options)).toArray(Bson[]::new);
    }

    public static <TItem> Bson pushEach(VoidKey statKey, List<TItem> values, PushOptions options) {
        return com.mongodb.client.model.Updates.pushEach(buildKey(statKey), values, options);
    }

    public static <K, TItem> Bson[] pushEach(IterableKey<K> iterableKey, K key, List<TItem> values, PushOptions options) {
        return iterableKey.stream().map(statKey -> pushEach(statKey, key, values, options)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson pushEach(Key<K> statKey, K key, List<TItem> values, PushOptions options) {
        return com.mongodb.client.model.Updates.pushEach(buildKey(statKey, key), values, options);
    }

    public static <TItem> Bson[] pull(IterableVoidKey iterableVoidKey, @Nullable TItem value) {
        return iterableVoidKey.stream().map(statKey -> pull(statKey, value)).toArray(Bson[]::new);
    }

    public static <TItem> Bson pull(VoidKey statKey, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.pull(buildKey(statKey), value);
    }

    public static <K, TItem> Bson[] pull(IterableKey<K> iterableKey, K key, @Nullable TItem value) {
        return iterableKey.stream().map(statKey -> pull(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson pull(Key<K> statKey, K key, @Nullable TItem value) {
        return com.mongodb.client.model.Updates.pull(buildKey(statKey, key), value);
    }

    public static Bson pullByFilter(final Bson filter) {
        return com.mongodb.client.model.Updates.pullByFilter(filter);
    }

    public static <TItem> Bson[] pullAll(IterableVoidKey iterableVoidKey, List<TItem> values) {
        return iterableVoidKey.stream().map(statKey -> pullAll(statKey, values)).toArray(Bson[]::new);
    }

    public static <TItem> Bson pullAll(VoidKey statKey, List<TItem> values) {
        return com.mongodb.client.model.Updates.pullAll(buildKey(statKey), values);
    }

    public static <K, TItem> Bson[] pullAll(IterableKey<K> iterableKey, K key, List<TItem> values) {
        return iterableKey.stream().map(statKey -> pullAll(statKey, key, values)).toArray(Bson[]::new);
    }

    public static <K, TItem> Bson pullAll(Key<K> statKey, K key, List<TItem> values) {
        return com.mongodb.client.model.Updates.pullAll(buildKey(statKey, key), values);
    }

    public static Bson[] popFirst(IterableVoidKey iterableVoidKey) {
        return iterableVoidKey.stream().map(Updates::popFirst).toArray(Bson[]::new);
    }

    public static Bson popFirst(VoidKey statKey) {
        return com.mongodb.client.model.Updates.popFirst(buildKey(statKey));
    }

    public static <K> Bson[] popFirst(IterableKey<K> iterableKey, K key) {
        return iterableKey.stream().map(statKey -> popFirst(statKey, key)).toArray(Bson[]::new);
    }

    public static <K> Bson popFirst(Key<K> statKey, K key) {
        return com.mongodb.client.model.Updates.popFirst(buildKey(statKey, key));
    }

    public static Bson[] popLast(IterableVoidKey iterableVoidKey) {
        return iterableVoidKey.stream().map(Updates::popLast).toArray(Bson[]::new);
    }

    public static Bson popLast(VoidKey statKey) {
        return com.mongodb.client.model.Updates.popLast(buildKey(statKey));
    }

    public static <K> Bson[] popLast(IterableKey<K> iterableKey, K key) {
        return iterableKey.stream().map(statKey -> popLast(statKey, key)).toArray(Bson[]::new);
    }

    public static <K> Bson popLast(Key<K> statKey, K key) {
        return com.mongodb.client.model.Updates.popLast(buildKey(statKey, key));
    }

    public static Bson[] bitwiseAnd(IterableVoidKey iterableVoidKey, int value) {
        return iterableVoidKey.stream().map(statKey -> bitwiseAnd(statKey, value)).toArray(Bson[]::new);
    }

    public static Bson bitwiseAnd(VoidKey statKey, int value) {
        return com.mongodb.client.model.Updates.bitwiseAnd(buildKey(statKey), value);
    }

    public static <K> Bson[] bitwiseAnd(IterableKey<K> iterableKey, K key, int value) {
        return iterableKey.stream().map(statKey -> bitwiseAnd(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K> Bson bitwiseAnd(Key<K> statKey, K key, int value) {
        return com.mongodb.client.model.Updates.bitwiseAnd(buildKey(statKey, key), value);
    }

    public static Bson[] bitwiseAnd(IterableVoidKey iterableVoidKey, long value) {
        return iterableVoidKey.stream().map(statKey -> bitwiseAnd(statKey, value)).toArray(Bson[]::new);
    }

    public static Bson bitwiseAnd(VoidKey statKey, long value) {
        return com.mongodb.client.model.Updates.bitwiseAnd(buildKey(statKey), value);
    }
    
    public static <K> Bson[] bitwiseAnd(IterableKey<K> iterableKey, K key, long value) {
        return iterableKey.stream().map(statKey -> bitwiseAnd(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K> Bson bitwiseAnd(Key<K> statKey, K key, long value) {
        return com.mongodb.client.model.Updates.bitwiseAnd(buildKey(statKey, key), value);
    }
    
    public static Bson[] bitwiseOr(IterableVoidKey iterableVoidKey, int value) {
        return iterableVoidKey.stream().map(statKey -> bitwiseOr(statKey, value)).toArray(Bson[]::new);
    }

    public static Bson bitwiseOr(VoidKey statKey, int value) {
        return com.mongodb.client.model.Updates.bitwiseOr(buildKey(statKey), value);
    }
    
    public static <K> Bson[] bitwiseOr(IterableKey<K> iterableKey, K key, int value) {
        return iterableKey.stream().map(statKey -> bitwiseOr(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K> Bson bitwiseOr(Key<K> statKey, K key, int value) {
        return com.mongodb.client.model.Updates.bitwiseOr(buildKey(statKey, key), value);
    }
    
    public static Bson[] bitwiseOr(IterableVoidKey iterableVoidKey, long value) {
        return iterableVoidKey.stream().map(statKey -> bitwiseOr(statKey, value)).toArray(Bson[]::new);
    }

    public static Bson bitwiseOr(VoidKey statKey, long value) {
        return com.mongodb.client.model.Updates.bitwiseOr(buildKey(statKey), value);
    }
    
    public static <K> Bson[] bitwiseOr(IterableKey<K> iterableKey, K key, long value) {
        return iterableKey.stream().map(statKey -> bitwiseOr(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K> Bson bitwiseOr(Key<K> statKey, K key, long value) {
        return com.mongodb.client.model.Updates.bitwiseOr(buildKey(statKey, key), value);
    }
    
    public static Bson[] bitwiseXor(IterableVoidKey iterableVoidKey, int value) {
        return iterableVoidKey.stream().map(statKey -> bitwiseXor(statKey, value)).toArray(Bson[]::new);
    }

    public static Bson bitwiseXor(VoidKey statKey, int value) {
        return com.mongodb.client.model.Updates.bitwiseXor(buildKey(statKey), value);
    }
    
    public static <K> Bson[] bitwiseXor(IterableKey<K> iterableKey, K key, int value) {
        return iterableKey.stream().map(statKey -> bitwiseXor(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K> Bson bitwiseXor(Key<K> statKey, K key, int value) {
        return com.mongodb.client.model.Updates.bitwiseXor(buildKey(statKey, key), value);
    }
    
    public static Bson[] bitwiseXor(IterableVoidKey iterableVoidKey, long value) {
        return iterableVoidKey.stream().map(statKey -> bitwiseXor(statKey, value)).toArray(Bson[]::new);
    } 

    public static Bson bitwiseXor(VoidKey statKey, long value) {
        return com.mongodb.client.model.Updates.bitwiseXor(buildKey(statKey), value);
    }
    
    public static <K> Bson[] bitwiseXor(IterableKey<K> iterableKey, K key, long value) {
        return iterableKey.stream().map(statKey -> bitwiseXor(statKey, key, value)).toArray(Bson[]::new);
    }

    public static <K> Bson bitwiseXor(Key<K> statKey, K key, long value) {
        return com.mongodb.client.model.Updates.bitwiseXor(buildKey(statKey, key), value);
    }

    private static <K> String buildKey(Key<K> statKey, K key) {
        return notNull("statKey", statKey).key(notNull("key", key));
    }

    private static String buildKey(VoidKey statKey) {
        return notNull("statKey", statKey).key();
    }
}
