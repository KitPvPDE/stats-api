package net.kitpvp.stats.bson;

import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import net.kitpvp.stats.keys.VoidStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public interface BsonWriter extends StatsWriter {

    Document bson();

    @Override
    default <T> void write(String key, T value) {
        BsonUtils.setValue(key, this.bson(), value);
    }

    default <K, U> void setBsonKey(Key<K> statKey, K key, U value, BsonEncoder<U> encoder) {
        this.write(statKey.key(key), encoder.encode(value).bson());
    }

    default <U> void setBsonKey(VoidKey statKey, U value, BsonEncoder<U> encoder) {
        this.setBsonKey(statKey, null, value, encoder);
    }

    default <K, U> void setBsonKeys(Key<K> statKey, K key, List<U> values, BsonEncoder<U> encoder) {
        this.write(statKey.key(key), values.stream().map(encoder::encode).map(BsonStatsWriter::bson).collect(Collectors.toList()));
    }

    default <U> void setBsonKeys(VoidKey statKey, List<U> values, BsonEncoder<U> encoder) {
        this.setBsonKeys(statKey, null, values, encoder);
    }

    @Override
    default <K, V> BsonWriter appendStatKey(StatsKey<K, V> statKey, K key, V value) {
        this.setStatKey(statKey, key, value);
        return this;
    }

    @Override
    default <V> BsonWriter appendStatKey(VoidStatsKey<V> statKey, V value) {
        this.setStatKey(statKey, value);
        return this;
    }

    @Override
    default <K, U> BsonWriter appendStatKey(Key<K> statKey, K key, U value) {
        this.setStatKey(statKey, key, value);
        return this;
    }

    @Override
    default <U> BsonWriter appendStatKey(VoidKey statKey, U value) {
        this.setStatKey(statKey, value);
        return this;
    }

    default <K, U> BsonWriter appendBsonKey(Key<K> statKey, K key, U value, BsonEncoder<U> encoder) {
        this.setBsonKey(statKey, key, value, encoder);
        return this;
    }

    default <U> BsonWriter appendBsonKey(VoidKey statKey, U value, BsonEncoder<U> encoder) {
        this.setBsonKey(statKey, value, encoder);
        return this;
    }

    default <K, U> BsonWriter appendBsonKeys(Key<K> statKey, K key, List<U> value, BsonEncoder<U> encoder) {
        this.setBsonKeys(statKey, key, value, encoder);
        return this;
    }

    default <U> BsonWriter appendBsonKeys(VoidKey statKey, List<U> value, BsonEncoder<U> encoder) {
        this.setBsonKeys(statKey, value, encoder);
        return this;
    }
}
