package net.kitpvp.stats.settings.impl;

import net.kitpvp.stats.settings.TSetting;

import java.util.Arrays;
import java.util.List;

public class ToggleSetting extends NormalSetting implements TSetting {

    private final String[] values;

    public ToggleSetting(String settingsKey, String settingsDefault, String... values) {
        super(settingsKey, settingsDefault);
        this.values = values;
    }

    @Override
    public List<String> getValues() {
        return Arrays.asList(this.values);
    }
}
