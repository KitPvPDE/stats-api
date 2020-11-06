package net.kitpvp.stats.season;

import com.google.gson.JsonElement;
import lombok.Getter;
import net.kitpvp.json.JsonConfig;

@Getter
public class Stage {

    private final int number;
    private final long start, end;

    public Stage(JsonElement element) {
        this.number = JsonConfig.readInteger(element, -1, "number");
        this.start = JsonConfig.readLong(element, -1, "start");
        this.end = JsonConfig.readLong(element, -1, "end");
    }

    public boolean isLive() {
        long time = System.currentTimeMillis();
        return time >= start && time <= end;
    }
}
