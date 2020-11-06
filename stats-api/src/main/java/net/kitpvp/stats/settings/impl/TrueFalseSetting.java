package net.kitpvp.stats.settings.impl;

public class TrueFalseSetting extends ToggleSetting {

    public TrueFalseSetting(String settingsKey, String settingsDefault) {
        super(settingsKey, settingsDefault, "true", "false");
    }
}
