package net.kitpvp.stats.keys.codec;

import net.kitpvp.stats.converter.Codec;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.Reader;

public interface CodecStatsKey<K, U> extends StatsKey<K, U> {

    Codec<U> codec();

    @Override
    default U extract(Reader statsReader, K key) {
        return statsReader.getStatReader(this, key).map(reader -> reader.map(this.codec())).orElse(this.def());
    }
}
