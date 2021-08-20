package net.kitpvp.stats.mongodb.model;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.conversions.Bson;

public final class Accumulators {

    public static Bson sum(String fieldName) {
        return new BsonDocument("$sum", new BsonString(fieldName));
    }

    public static Bson sum(int i) {
        return new BsonDocument("$sum", new BsonInt32(i));
    }

    public static Bson count() {
        return sum(1);
    }
}
