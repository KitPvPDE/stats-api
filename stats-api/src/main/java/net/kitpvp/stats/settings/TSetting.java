package net.kitpvp.stats.settings;

import java.util.List;

public interface TSetting extends Setting {

    List<String> getValues();

    default String next(String current) {
        List<String> values = this.getValues();
        return values.get((values.indexOf(current) + 1) % values.size());
    }
}
