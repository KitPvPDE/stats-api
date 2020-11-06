package net.kitpvp.stats.api;

import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.query.WriteQuery;

import java.util.UUID;

public interface Writable<Source, T> {
    
    WriteQuery<T> write(Database database);
    
}
