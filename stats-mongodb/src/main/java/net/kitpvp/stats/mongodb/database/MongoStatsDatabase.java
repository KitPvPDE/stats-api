package net.kitpvp.stats.mongodb.database;

import com.koloboke.collect.map.ObjIntMap;
import com.koloboke.collect.map.hash.HashObjIntMaps;
import com.koloboke.collect.set.ObjSet;
import com.koloboke.collect.set.hash.HashObjSets;
import com.mongodb.client.model.BsonField;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.keys.LongSeasonKey;
import net.kitpvp.stats.keys.LongStatsKey;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.connection.MongoDBConnection;
import net.kitpvp.stats.mongodb.model.Indexes;
import net.kitpvp.stats.season.Season;
import org.bson.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.kitpvp.stats.mongodb.model.Accumulators.sum;
import static net.kitpvp.stats.mongodb.model.Aggregates.*;
import static net.kitpvp.stats.mongodb.model.Filters.*;
import static net.kitpvp.stats.mongodb.model.Updates.inc;

public class MongoStatsDatabase {

    protected static final String STATS_DATABASE_NAME = "stats_db";
    protected static final String OVER_TIME_COLLECTION_NAME = "_over_time";
    protected static final String INCREMENTAL_COLLECTION_NAME = "_incremental";
    protected static final String RANKING_COLLECTION_NAME = "_ranking_";

    protected static final LongSeasonKey<String> statByAction = LongStatsKey.<String>builder()
            .seasonKeyMapping((actionKeyFunction, integer) -> new SeasonKeyFunction<>(actionKeyFunction, integer == 0 ? "alltime" : "season" + integer))
            .keyBuilder(builder -> builder.key(Key.identity()))
            .season();

    protected static ObjIntMap<String> mapFromArray(String[] strings) {
        ObjIntMap<String> map = HashObjIntMaps.newMutableMap();
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], i);
        }
        return map;
    }

    private final String name;
    private final ObjIntMap<String> keys;
    private final ObjSet<String> unary;
    private final MongoDBConnection connection;
    private final MongoDBCollection incremental;
    private final MongoDBCollection overTime;

    public MongoStatsDatabase(String name, String[] keys, Predicate<String> unary, MongoDBConnection connection, boolean incremental, boolean overTime) {
        this(name, mapFromArray(keys), unary, connection, incremental, overTime);
    }

    public MongoStatsDatabase(String name, ObjIntMap<String> keys, Predicate<String> unary, MongoDBConnection connection, boolean incremental, boolean overTime) {
        this.name = name;
        this.keys = keys;
        this.unary = keys.keySet().stream().filter(unary).collect(Collectors.toCollection(HashObjSets::newMutableSet));
        this.connection = connection;
        this.incremental = !incremental ? null : connection.getMongoCollection(STATS_DATABASE_NAME, name + INCREMENTAL_COLLECTION_NAME);
        this.overTime = !overTime ? null : connection.getMongoCollection(STATS_DATABASE_NAME, name + OVER_TIME_COLLECTION_NAME);

        if(this.incremental != null) {
            this.checkIndexes(this.incremental);
        }

        if(this.overTime != null) {
            this.checkIndexes(this.overTime);
        }
    }

    public String getName() {
        return name;
    }

    public MongoDBConnection getConnection() {
        return connection;
    }

    public MongoDBCollection getIncrementalCollection() {
        return incremental;
    }

    public MongoDBCollection getOverTimeCollection() {
        return overTime;
    }

    public void addStat(UUID uuid, String key, long value, Document data) {
        this.checkKey(key);

        if (this.overTime != null) {
            this.overTime.insert().insert(new Document()
                    .append("time", new Date())
                    .append("uuid", uuid)
                    .append("action", this.keys.getInt(key))
                    .append(key, value), data
            ).execute();
        }

        if (this.incremental != null) {
            this.incremental.write()
                    .filter(eq("_id", uuid))
                    .upsert(true)
                    .update(inc(statByAction, key, value))
                    .execute();
        }
    }

    public long getStat(UUID uuid, String key, long before, TimeUnit unit) {
        this.checkKey(key);

        Date date = new Date(System.currentTimeMillis() - unit.toMillis(before));
        if (this.isUnary(key)) {
            return this.getOverTimeCollection().count().filters(
                    eq("uuid", uuid),
                    eq("action", getKeyId(key)),
                    gte("time", date)).count();
        } else {
            return this.getOverTimeCollection().aggregate().pipeline(
                    match(
                            and(
                                    eq("uuid", uuid),
                                    eq("action", getKeyId(key)),
                                    gte("time", date)
                            )
                    ),
                    group(
                            null,
                            field(key, sum("$" + key))
                    )
            ).firstOrElseGet(BsonStatsReader::new).getLongKey(key, 0);
        }
    }

    public StatsReader getStats(UUID uuid) {
        return this.getIncrementalCollection().find()
                .filter(eq("_id", uuid))
                .firstOrElseGet(BsonStatsReader::new);
    }

    public StatsReader getStats(UUID uuid, long before, TimeUnit unit) {
        return getStats(uuid, this.keys.keySet(), before, unit);
    }

    public StatsReader getStats(UUID uuid, Set<String> keys, long before, TimeUnit unit) {
        Date date = new Date(System.currentTimeMillis() - unit.toMillis(before));

        List<BsonField> fields = keys.stream().map(key -> field(key, sum("$" + key))).collect(Collectors.toList());

        return this.getOverTimeCollection().aggregate()
                .pipeline(
                        match(
                                and(
                                        eq("uuid", uuid),
                                        gte("time", date)
                                )
                        ),
                        group(null,
                                fields)
                )
                .firstOrElseGet(BsonStatsReader::new);
    }

    public long getStat(UUID uuid, String key) {
        return getStat(uuid, key, Season.ALLTIME);
    }

    public long getStat(UUID uuid, String key, int season) {
        this.checkKey(key);
        return incremental.find()
                .filter(eq("_id", uuid))
                .firstOrElseGet(BsonStatsReader::new)
                .getLongKey(statByAction, key, season);
    }

    public long getStat(UUID uuid, String key, long before, TimeUnit unit, long duration, TimeUnit durationUnit) {
        this.checkKey(key);

        Date beforeDate = new Date(System.currentTimeMillis() - unit.toMillis(before));
        Date afterDate = new Date(System.currentTimeMillis() - unit.toMillis(before) + durationUnit.toMillis(duration));
        if (this.isUnary(key)) {
            return overTime.count().filters(
                    eq("uuid", uuid),
                    eq("action", key),
                    gte("time", beforeDate),
                    lte("time", afterDate)
            ).count();
        } else {
            return overTime.aggregate().pipeline(
                    match(
                            and(
                                    eq("uuid", uuid),
                                    gte("time", beforeDate),
                                    lte("time", afterDate),
                                    eq("action", key)
                            )
                    ),
                    group(
                            null,
                            field(key, sum("$value"))
                    )
            ).firstOrElseGet(BsonStatsReader::new).getLongKey(key, 0);
        }
    }

    protected void checkIndexes(MongoDBCollection collection) {
        collection.index().fields(Indexes.descending("time")).create();
        collection.index().fields(Indexes.ascending("uuid", "action")).create();
    }

    protected int getKeyId(String key) {
        return this.keys.getInt(key);
    }

    protected boolean isUnary(String key) {
        return this.unary.contains(key);
    }

    protected void checkKey(String key) {
        if (!this.keys.containsKey(key))
            throw new IllegalArgumentException("key " + key);
    }
}
