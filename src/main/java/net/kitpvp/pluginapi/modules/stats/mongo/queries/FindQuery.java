package net.kitpvp.pluginapi.modules.stats.mongo.queries;

import com.mongodb.Function;
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


public class FindQuery implements Iterable<StatsReader> {

    private final Function<Document, StatsReader> mapper;
    private final FindIterable<Document> iterable;

    public FindQuery(Function<Document, StatsReader> mapper, MongoCollection collection) {
        this.iterable = collection.getCollection().find();
        this.mapper = mapper;
    }

    public <K, V> FindQuery(Function<Document, StatsReader> mapper, MongoCollection collection, StatsKey<K, V> statsKey, K k, V v, Comparison comparison) {
        this.iterable = collection.getCollection().find(new Document(statsKey.getKey(k), new Document("$" + comparison.getCommand(), v)));
        this.mapper = mapper;
    }

    public <V> FindQuery(Function<Document, StatsReader> mapper, MongoCollection collection, SStatsKey<V> statsKey, V v, Comparison comparison) {
        this.iterable = collection.getCollection().find(new Document(statsKey.getKey(), new Document("$" + comparison.getCommand(), v)));
        this.mapper = mapper;
    }

    public FindQuery limit(int limit) {
        this.iterable.limit(limit);
        return this;
    }

    public FindQuery skip(int skip) {
        this.iterable.skip(skip);
        return this;
    }

    public FindQuery sort(SStatsKey<?> statsKey, Order order) {
        return this.sort(statsKey, null, order);
    }

    public <K> FindQuery sort(StatsKey<K, ?> statsKey, K k, Order order) {
        this.iterable.sort(new Document(statsKey.getKey(k), order.getOrder()));
        return this;
    }

    public MongoIterable<StatsReader> find() {
        return this.iterable.map(this.mapper);
    }

    public StatsReader first() {
        MongoIterable<StatsReader> iterable = this.iterable.map(this.mapper);

        return iterable.first();
    }

    @Override
    public MongoCursor<StatsReader> iterator() {
        MongoIterable<StatsReader> iterable = this.find();

        return iterable.cursor();
    }
}
