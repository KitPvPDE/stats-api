package net.kitpvp.stats.api;

import net.kitpvp.stats.query.CountQuery;

public interface Countable<Source, T> {

    CountQuery<T> count(Source source);

}
