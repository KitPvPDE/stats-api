package net.kitpvp.pluginapi.modules.stats.setting;

import net.kitpvp.pluginapi.modules.stats.TSetting;

import java.util.Arrays;
import java.util.List;

public class ToggleSetting extends NormalSetting implements TSetting {

    private final String[] values;

    public ToggleSetting(String settingsKey, String settingsDefault, boolean bungee, boolean local, String... values) {
        super(settingsKey, settingsDefault, bungee, local);
        this.values = values;
    }

    public List<String> settingsValues() {
        return Arrays.asList(this.values);
    }
}
