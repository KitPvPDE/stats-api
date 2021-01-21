package net.kitpvp.stats.bson;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.api.keys.Entry;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.bson.codec.BsonDecoder;
import net.kitpvp.stats.converter.Converter;
import net.kitpvp.stats.converter.Decoder;
import org.bson.Document;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static net.kitpvp.stats.api.keys.Entry.entry;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "database")
public class BsonStatsReader implements BsonReader {

    private final Document database;

    public BsonStatsReader() {
        this(new Document());
    }

    public Document bson() {
        return this.database;
    }
}
