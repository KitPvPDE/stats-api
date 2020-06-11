package net.kitpvp.pluginapi.modules.stats.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import net.kitpvp.mongodbapi.MongoCollection;
import net.kitpvp.pluginapi.modules.stats.StatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.find.Comparison;
import net.kitpvp.pluginapi.modules.stats.mongo.find.Order;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

public class MongoQuery implements Iterable<StatsReader> {

    private final FindIterable<Document> iterable;

    public MongoQuery(MongoCollection collection) {
        this.iterable = collection.getCollection().find();
    }

    public <K, V> MongoQuery(MongoCollection collection, StatsKey<K, V> statsKey, K k, V v, Comparison comparison) {
        this.iterable = collection.getCollection().find(new Document(statsKey.getKey(k), new Document("$" + comparison.getCommand(), v)));
    }

    public <V> MongoQuery(MongoCollection collection, SStatsKey<V> statsKey, V v, Comparison comparison) {
        this.iterable = collection.getCollection().find(new Document(statsKey.getKey(), new Document("$" + comparison.getCommand(), v)));
    }

    public MongoQuery limit(int limit) {
        this.iterable.limit(limit);
        return this;
    }

    public MongoQuery skip(int skip) {
        this.iterable.skip(skip);
        return this;
    }

    public MongoQuery sort(SStatsKey<?> statsKey, Order order) {
        return this.sort(statsKey, null, order);
    }

    public <K> MongoQuery sort(StatsKey<K, ?> statsKey, K k, Order order) {
        this.iterable.sort(new Document(statsKey.getKey(k), order.getOrder()));
        return this;
    }

    public MongoIterable<StatsReader> find() {
        return this.iterable.map(MongoStatsReader::new);
    }

    public StatsReader first() {
        MongoIterable<StatsReader> iterable = this.iterable.map(MongoStatsReader::new);

        return iterable.first();
    }

    @Override
    public MongoCursor<StatsReader> iterator() {
        MongoIterable<StatsReader> iterable = this.find();

        return iterable.cursor();
    }
}
