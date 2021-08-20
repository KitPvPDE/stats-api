package net.kitpvp.stats.mongodb.database;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.connection.MongoDBConnection;
import net.kitpvp.stats.mongodb.cursor.IterableCursor;
import net.kitpvp.stats.mongodb.model.Indexes;
import net.kitpvp.stats.season.Season;
import org.bson.Document;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Updates.set;
import static net.kitpvp.stats.mongodb.database.MongoStatsDatabase.*;
import static net.kitpvp.stats.mongodb.model.Aggregates.*;
import static net.kitpvp.stats.mongodb.model.Filters.*;
import static net.kitpvp.stats.mongodb.model.Sorts.descending;
import static net.kitpvp.stats.mongodb.model.Indexes.*;

public class MongoRankingLoader {

    private static final String STATS_UUID_FIELD = "$uuid";

    private final MongoStatsDatabase statsDatabase;
    private final MongoDBCollection ranking, lifetimeRanking;
    private final long durationInMillis;
    private final String action;

    public MongoRankingLoader(MongoStatsDatabase statsDatabase, String action, long duration, TimeUnit unit) {
        this.statsDatabase = statsDatabase;
        this.action = action;
        MongoDBConnection connection = statsDatabase.getConnection();
        this.lifetimeRanking = connection.getMongoCollection(STATS_DATABASE_NAME, statsDatabase.getName() + RANKING_COLLECTION_NAME + "lifetime");
        this.durationInMillis = unit.toMillis(duration);

        if (durationInMillis > 0) {
            this.ranking = connection.getMongoCollection(STATS_DATABASE_NAME, statsDatabase.getName() + RANKING_COLLECTION_NAME + duration + "" + unit.name().toLowerCase());
            long deleted = this.ranking.delete()
                    .filter(lt("timestamp", new Date(System.currentTimeMillis() - durationInMillis)))
                    .delete(true);

            System.out.println("removed " + deleted + " outdated ranking entries");
        } else {
            this.ranking = null;
        }
    }

    public void reworkRanking() {
        this.reworkRanking(this.lifetimeRanking, Long.MAX_VALUE);

        if(this.ranking != null) {
            this.reworkRanking(this.ranking, this.durationInMillis);
        }
    }

    private void reworkRanking(MongoDBCollection collection, long durationInMillis) {
        long total = statsDatabase.getIncrementalCollection().count().count();
        if(durationInMillis == Long.MAX_VALUE) {
            System.out.println("Updating lifetime ranking, " + total + " documents");
        } else {
            System.out.println("Updating ranking for " + TimeUnit.MILLISECONDS.toDays(durationInMillis) + " days, " + total + " documents");
        }
        collection.index().fields(Indexes.descending("stat")).create();

        long one_percent = total / 100;
        long next_percent = one_percent;
        long done = 0;
        for(UUID uuid : this.statsDatabase.getIncrementalCollection().find().findAndMap((StatsReader statsReader) ->
                statsReader.getStatKey("_id", (UUID) null))) {
            long stat = this.statsDatabase.getStat(uuid, action, durationInMillis, TimeUnit.MILLISECONDS);
            collection.write()
                    .filter(eq("_id", uuid))
                    .update(
                            set("timestamp", new Date()),
                            set("stat", stat)
                    )
                    .execute();

            done++;

            if(done >= next_percent) {
                next_percent += one_percent;
                System.out.println("[STATUS] " + done +"/" + total);
            }
        }

        System.out.println("[STATUS] " + done + "/" + total);
    }

    public StatsReader getStatRankAt(int position, int season) {
        return statsDatabase.getIncrementalCollection().find()
                .sort(descending(statByAction.season(season), action))
                .skip(position - 1)
                .limit(1)
                .first();
    }

    public StatsReader getStatRankAt(int position, long before, TimeUnit unit) {
        Date beforeDate = new Date(System.currentTimeMillis() - unit.toMillis(before));

        return statsDatabase.getOverTimeCollection().aggregate()
                .pipeline(
                        match(
                                and(
                                        eq("action", statsDatabase.getKeyId(action)),
                                        gte("time", beforeDate)
                                )
                        ),
                        group(
                                STATS_UUID_FIELD,
                                field("actions", new Document("$sum", "$" + action))
                        ),
                        sort(
                                descending("actions")
                        ),
                        skip(
                                position - 1
                        ),
                        limit(
                                1
                        )
                )
                .first();
    }

    public IterableCursor<StatsReader> getStatRanks(int from, int entries, long before, TimeUnit unit) {
        Date beforeDate = new Date(System.currentTimeMillis() - unit.toMillis(before));

        return statsDatabase.getOverTimeCollection().aggregate()
                .pipeline(
                        match(
                                and(
                                        eq("action", statsDatabase.getKeyId(action)),
                                        gte("time", beforeDate)
                                )
                        ),
                        group(
                                STATS_UUID_FIELD,
                                field("actions", new Document("$sum", "$" + action))
                        ),
                        sort(
                                descending("actions")
                        ),
                        skip(
                                from - 1
                        ),
                        limit(
                                entries
                        )
                )
                .aggregate();
    }

    public long getStatRank(UUID uuid) {
        return this.getStatRank(uuid, Season.ALLTIME);
    }

    public long getStatRank(UUID uuid, int season) {
        long stat = this.statsDatabase.getStat(uuid, action, season);
        return this.statsDatabase.getIncrementalCollection().count()
                .filters(
                        gt(statByAction.season(season), action, stat)
                )
                .count() + 1;
    }

    public long getStatRank(UUID uuid, long before, TimeUnit unit) {
        long stat = this.statsDatabase.getStat(uuid, action, before, unit);
        String ident = "" + before + unit.name().toLowerCase();

        MongoDBCollection collection = null;
        if (before == Long.MAX_VALUE) {
            collection = this.lifetimeRanking;
        } else if (unit.toMillis(before) == durationInMillis) {
            collection = this.ranking;
        }

        if (collection != null) {
            collection.write()
                    .filter(
                            eq(uuid)
                    )
                    .update(
                            set("timestamp", new Date()),
                            set("stat", stat)
                    )
                    .upsert(true)
                    .execute();

            return collection.count()
                    .filters(
                            gt("stat", stat),
                            gt("timestamp", new Date(System.currentTimeMillis() - unit.toMillis(before)))
                    )
                    .count() + 1;
        } else {
            Date beforeDate = new Date(System.currentTimeMillis() - unit.toMillis(before));
            return this.statsDatabase.getOverTimeCollection().aggregate()
                    .pipeline(
                            match(
                                    and(
                                            eq("action", statsDatabase.getKeyId(action)),
                                            gte("time", beforeDate)
                                    )
                            ),
                            group(
                                    STATS_UUID_FIELD,
                                    field("actions", new Document("$sum", "$" + action))
                            ),
                            match(
                                    gt("actions", stat)
                            ),
                            group(
                                    null,
                                    field("players", new Document("$sum", "1"))
                            )
                    )
                    .firstOrElseGet(BsonStatsReader::new).getLongKey("players", 0) + 1;
        }
    }
}
