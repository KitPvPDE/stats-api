## Documentation

Stats API to integrate MongoDB with Java for **easy**, **typesafe** usage.

## Examples

### Keys, Readers

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

Mongo.find(database,collection)
        .filter(gte(statsKey,300))
        .limit(2)
        .skip(1);
```

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


