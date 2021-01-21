package net.kitpvp.stats.reader;

public interface KeysReader extends Reader {

    /*

    Syntax:

    statsReader.map(converter)

    CosmeticType -> Enum
    Cosmetic -> interface

    this.cosmetics = statsReader.getKeys(Stats.COSMETIC)
    -> Set<CosmeticType>

    BiFunction<StatsReader, CosmeticType, Cosmetic> function = statsReader -> null;
    this.cosmetics = statsReader.getStatKeys(Stats.COSMETIC)
    -> Set<Cosmetic>

    this.cosmetics = statsReader.getStatEntries(Stats.COSMETIC)
    -> Set<Entry<CosmeticType, Cosmetic>>

     */

    /*
    default <K, V, U> Set<U> getKeys(StatsKey<K, V> statsKey, Function<String, U> function) {
        String prefix = statsKey.keyFunction().prefix();

        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            map = this.find(prefix, new Document());
        } else {
            map = this.bson();
        }


        return map.keySet().stream().map(function).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    default <K, V, U> Set<U> getKeys(StatsKey<K, V> statsKey, K k, Function<String, U> function) {
        return this.getKeys(statsKey, function);
    }

    default <K, V> Set<String> getKeys(StatsKey<K, V> statsKey) {
        return this.getKeys(statsKey, Function.identity());
    }

    default <K, V> Set<String> getKeys(StatsKey<K, V> statsKey, K k) {
        return this.getKeys(statsKey, Function.identity());
    }
     */
}
