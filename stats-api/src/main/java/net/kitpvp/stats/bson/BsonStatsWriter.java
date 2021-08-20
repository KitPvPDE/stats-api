package net.kitpvp.stats.bson;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.Document;

@RequiredArgsConstructor
@ToString(of = "database")
@EqualsAndHashCode(of = "database")
public class BsonStatsWriter implements BsonWriter {

    private final Document database;
    private boolean isNull = false;

    public BsonStatsWriter() {
        this(new Document());
    }

    public Document bson() {
        if(this.isNull) {
            return null;
        }
        return this.database;
    }

    /*
    @Override
    public void writeNull() {
        this.isNull = true;
    }
    */
}
