package net.kitpvp.stats.settings.impl;

import net.kitpvp.stats.settings.Setting;

import java.util.HashMap;
import java.util.Map;

public class NormalSetting implements Setting {

    private static final Map<String, Setting> settings = new HashMap<>();

    private final String key, def;

    public NormalSetting(String settingsKey, String settingsDefault) {
        this.key = settingsKey;
        this.def = settingsDefault;

        settings.put(settingsKey, this);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getDefault() {
        return this.def;
    }

    public static Setting getSetting(String settingsKey) {
        return settings.get(settingsKey);
    }

    public static <T extends Setting> T getSetting(String settingsKey, Class<T> settingsClass) {
        return settingsClass.cast(getSetting(settingsKey));
    }
}
