package net.kitpvp.stats.local;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.bson.BsonStats;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.bson.BsonUtils;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.local.query.LocalWriteQuery;
import net.kitpvp.stats.query.CountQuery;
import net.kitpvp.stats.query.FindQuery;
import org.bson.Document;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LocalStats extends BsonStats implements Stats {

    @Getter
    private final UUID playerId;

    public LocalStats(UUID playerId) {
        super(new Document());
        this.playerId = playerId;
    }

    @Override
    public LocalStats load() {
        return this;
    }

    public LocalStats update(Document database) {
        this.bson(database);
        return this;
    }

    public LocalWriteQuery write() {
        return new LocalWriteQuery(this);
    }

    @Deprecated
    public Document source() {
        return this.bson();
    }
}
