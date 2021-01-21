package net.kitpvp.stats.bson;

import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.api.keys.VoidKey;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public interface BsonWriter<W extends BsonWriter<W>> extends StatsWriter {

    Document bson();

    W writer();

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
    default <K, V> W appendStatKey(StatsKey<K, V> statKey, K key, V value) {
        this.setStatKey(statKey, key, value);
        return this.writer();
    }

    @Override
    default <V> W appendStatKey(SStatsKey<V> statKey, V value) {
        this.setStatKey(statKey, value);
        return this.writer();
    }

    @Override
    default <K, U> W appendStatKey(Key<K> statKey, K key, U value) {
        this.setStatKey(statKey, key, value);
        return this.writer();
    }

    @Override
    default <U> W appendStatKey(VoidKey statKey, U value) {
        this.setStatKey(statKey, value);
        return this.writer();
    }

    default <K, U> W appendBsonKey(Key<K> statKey, K key, U value, BsonEncoder<U> encoder) {
        this.setBsonKey(statKey, key, value, encoder);
        return this.writer();
    }

    default <U> W appendBsonKey(VoidKey statKey, U value, BsonEncoder<U> encoder) {
        this.setBsonKey(statKey, value, encoder);
        return this.writer();
    }

    default <K, U> W appendBsonKeys(Key<K> statKey, K key, List<U> value, BsonEncoder<U> encoder) {
        this.setBsonKeys(statKey, key, value, encoder);
        return this.writer();
    }

    default <U> W appendBsonKeys(VoidKey statKey, List<U> value, BsonEncoder<U> encoder) {
        this.setBsonKeys(statKey, value, encoder);
        return this.writer();
    }
}
