package net.kitpvp.stats.local;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.local.query.LocalWriteQuery;
import net.kitpvp.stats.query.CountQuery;
import net.kitpvp.stats.query.FindQuery;
import org.bson.Document;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocalStats implements Stats<Void, LocalStats>, StatsReader, StatsWriter {

    @Getter
    private final UUID playerId;
    private final Document database = new Document();

    @Override
    public <V> V find(String key, V def) {
        return DocumentUtils.getValue(key, this.database, def);
    }

    @Override
    public <T> void write(String key, T value) {
        DocumentUtils.setValue(key, this.database, value);
    }

    @Override
    public LocalStats load(Void unused) {
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
        return this.database;
    }

    @Override
    public <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, Function<String, U> function) {
        String prefix = statsKey.keyFunction().prefix();
        if(prefix == null)
            throw new NullPointerException("prefix");

        Map<String, Object> map;
        if(!prefix.isEmpty())
            map = this.find(prefix, new HashMap<>());
        else
            map = this.database;

        return map.keySet().stream().map(function).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
