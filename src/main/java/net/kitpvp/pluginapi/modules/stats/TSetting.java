package net.kitpvp.pluginapi.modules.stats;

import java.util.List;

public interface TSetting extends Setting {

    List<String> settingsValues();

    default String next(String current) {
        List<String> values = this.settingsValues();
        return values.get((values.indexOf(current) + 1) % values.size());
    }
}
