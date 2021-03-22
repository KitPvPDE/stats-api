package net.kitpvp.stats.api.numbers;

import net.kitpvp.stats.keys.DoubleStatsKey;
import net.kitpvp.stats.keys.IntStatsKey;
import net.kitpvp.stats.keys.LongStatsKey;
import net.kitpvp.stats.reader.Reader;

public class NumberConversions {

    public static <K> long getLong(LongStatsKey<K> statsKey, K key, Reader statsReader) {
        Number number = statsReader.find(statsKey.key(key), null);
        if(number == null)
            return statsKey.defLong();

        if(number instanceof Long)
            return (long) number;

        return number.longValue();
    }

    public static <K> double getDouble(DoubleStatsKey<K> statsKey, K key, Reader statsReader) {
        Number number = statsReader.find(statsKey.key(key), null);
        if(number == null)
            return statsKey.defDouble();

        if(number instanceof Double)
            return (double) number;

        return number.doubleValue();
    }

    public static <K> int getInt(IntStatsKey<K> statsKey, K key, Reader statsReader) {
        Number number = statsReader.find(statsKey.key(key), null);
        if(number == null)
            return statsKey.defInt();

        if(number instanceof Integer)
            return (int) number;

        return number.intValue();
    }
}
