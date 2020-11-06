package net.kitpvp.stats.api;

import net.kitpvp.stats.query.FindQuery;

import java.util.UUID;

public interface Findable<Source, T> {
    
    FindQuery<T> find(Source source);
    
}
