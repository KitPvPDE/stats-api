## Documentation

Stats API to integrate MongoDB with Java for **easy**, **typesafe** usage.

## Examples

### Keys, Readers

There are predefined keys in every ``StatsKey`` class, accessible via e.g. `LongStatsKey.identity()`, that do provide
the default behaviour of each key. You might want to use them in the following fashion:

```java
int data=statsReader.getLongKey(LongStatsKey.identity(),"prefixed.key.suffix");
```
How you retrieve ``StatsReader`` objects and use them in a more individualized fashion will now be told below.

If you want to create new, individualized keys you can do that in the following way:

```java
Key<String> key=Key.<String>builder()
        .prefix("prefix")
        .function(Function.identity())
        .build();

        IntStatsKey<String> statsKey=IntStatsKey.<String>builder()
        .keyBuilder(builder->builder.key(key))
        .offset(100)
        .build();
```

Will create a *IntStatsKey* searching for entries matching `"prefix.key"` where ``"key"``
is the input parameter supplied when searching in a ``StatsReader``. Individual subclasses of `StatsKey` exist for every
primitive type to ensure maximum performance and to reduce type conversions.

Lets assume we have the following database:

````json
{
  "balance": {
    "ernie": 49,
    "barbara": 204,
    "rudolf": -30
  }
}
````

Lets load this into
a [StatsReader](https://github.com/KitPvPDE/stats-api/blob/master/stats-api/src/main/java/net/kitpvp/stats/StatsReader.java)
. To do this, we choose
a [BsonStatsReader](https://github.com/KitPvPDE/stats-api/blob/master/stats-api/src/main/java/net/kitpvp/stats/bson/BsonStatsReader.java):

````java
StatsReader statsReader=new BsonStatsReader(
        Document.parse("{'balance': {'ernie': 49, 'barbara': 204, 'rudolf': -30}}"))
````

Now that we have loaded our data into the ``StatsReader``, we can start extracting data from it.

````java
int balanceOfBarbara=statsReader.getIntKey(statsKey,"barbara"); // 304
        int balanceOfErnie=statsReader.getIntKey(statsKey,"ernie"); // 149

        if(balanceOfBarbara>balanceOfErnie){
// do something    
        }
````

Until now, we only used keys that do exist in our Database. Now lets query for a key which is not present

```java
int balanceOfKlaus=statsReader.getIntKey(statsKey,"klaus");
```

This will work properly, since when we created our ``IntStatsKey``, we specified no changed default value and an offset
of ``100``. `balanceOfKlaus` will therefor be `0+100`

### MongoDB

To perform actions in any database, you will need to have established a database connection using
[MongoDB-API](https://repo.kitpvp.de)

### Find Queries

Now, lets use our previously created `IntStatsKey` to filter the database for entries with balance >= 300, a limit of 2
entries and one skipped entry.

```java
import static net.kitpvp.stats.mongodb.model.Filters.gte;

Iterable<StatsReader> iterable = Mongo.find(database,collection)
        .filter(gte(statsKey,300))
        .limit(2)
        .skip(1);
```

We now can iterate the resulting documents in a simple ``for-each loop``

````java
for(StatsReader statsReader : iterable) {
    // do something    
}
````

All other queries are aswell possible to be executed via the ``Mongo`` class, 
the central point of the MongoDB API. This includes ``count``, `update`, `bulk` and `delete` operations.

For every sync operation there is also a simple handle to have it executed asynchronous,
with or without `Callbacks` and specific ``Executors`` to have the callbacks executed in.

Here is a quick example on how to do so:

````java
// We want up increment the key "balance" in every document
// in our collection by 5, when the "balance" is already higher
// than 10, and we want to use a callback to print something when
// the operation is complete.

import static net.kitpvp.stats.mongodb.model.Filters.gte;
import static net.kitpvp.stats.mongodb.model.Updates.inc;

Mongo.write(database, collection)
    .filter(gte(Key.identity(), "balance", 10))
    .update(inc(IntStatsKey.identity(), "balance", 5))
    .executeAsync(() -> System.out.println("done"));
````

### Filters, Updates and Sorts

all have their own class in
the [net.kitpvp.stats.mongodb.model](https://github.com/KitPvPDE/stats-api/tree/master/stats-mongodb/src/main/java/net/kitpvp/stats/mongodb/model)
package. Each `mongodb` operation has a corresponding method, aswell as every sort operation and query filter.

For simple usage, just static import those methods needed:

````java
import static net.kitpvp.stats.mongodb.model.Updates.inc;
import static net.kitpvp.stats.mongodb.model.Updates.unset;

import static net.kitpvp.stats.mongodb.model.Sorts.ascending;
import static net.kitpvp.stats.mongodb.model.Sorts.orderedBy;

import static net.kitpvp.stats.mongodb.model.Filters.*;
````


