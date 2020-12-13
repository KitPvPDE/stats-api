package net.kitpvp.stats.reader;

import net.kitpvp.stats.converter.Converter;

public interface MappingReader extends Reader {

    <T> T mapStatsReader(Converter<T> converter);
}
