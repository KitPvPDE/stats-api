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

public class LocalStats extends BsonStats implements Stats<Void, LocalStats>, StatsReader, StatsWriter {

    @Getter
    private final UUID playerId;

    public LocalStats(UUID playerId) {
        super(new Document());
        this.playerId = playerId;
    }

    @Override
    public LocalStats load(Void unused) {
        return this;
    }

    public LocalStats update(Document database) {
        this.bson(database);
        return this;
    }

    @Override
    public FindQuery<LocalStats> find(Void unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CountQuery<LocalStats> count(Void unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LocalWriteQuery write(Void database) {
        return new LocalWriteQuery(this);
    }

    public Document source() {
        return this.bson();
    }
}
