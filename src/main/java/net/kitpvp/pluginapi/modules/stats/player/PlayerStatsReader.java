package net.kitpvp.pluginapi.modules.stats.player;

import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.StatsReader;

@RequiredArgsConstructor
public class PlayerStatsReader implements StatsReader {

    private final StatsReader localStatsReader, mongoStatsReader;

    @Override
    public <V> V find(String key, V def) {
        V value = this.localStatsReader.find(key, null);
        if(value != null)
            return value;

        return this.mongoStatsReader != null ? this.mongoStatsReader.find(key, def) : def;
    }

    @Override
    public Object getSource() {
        return this.mongoStatsReader != null ? this.mongoStatsReader.getSource() : this.localStatsReader.getSource();
    }
}
