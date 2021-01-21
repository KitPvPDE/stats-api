package net.kitpvp.stats.query;

import net.kitpvp.stats.query.insert.Insert;

public interface InsertQuery<T> {

    InsertQuery<T> insert(Insert<T>[] inserts);

}
