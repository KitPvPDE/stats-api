package net.kitpvp.stats.mongodb.query.update.updates;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.query.update.ArrayAction;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import org.bson.Document;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
class ArrayUpdateImpl<K, X> implements MongoUpdate {

    private final AppendableArrayKey<K, X> statsKey;
    private final K k;
    private final List<X> v;
    private final ArrayAction operator;

    public ArrayUpdateImpl(AppendableArrayKey<K, X> statsKey, K k, Supplier<List<X>> supplier, X[] xs, ArrayAction operator) {
        this.statsKey = statsKey;
        this.k = k;
        this.v = Stream.of(xs).collect(Collectors.toCollection(supplier));
        this.operator = operator;
    }

    @Override
    public MongoUpdate append(MongoStatsReader statsReader) {
        this.statsKey.append(this.k, this.v, (statsKey, key, value) -> {
            Document document = this.document(statsReader.source(), this.operator);
            String builtKey = statsKey.key(key);
            Document append = document.get(builtKey, Document.class);
            if(append == null) {
                document.put(builtKey, (append = new Document()));
            }
            if(append.containsKey(this.operator.getMongoArrayOperator())) {
                throw new IllegalArgumentException(String.format("Cannot update key %s (%s), key already set (%s)", statsKey, builtKey, document));
            } else {
                append.put(this.operator.getMongoArrayOperator(), value);
            }
        });
        return this;
    }
}
