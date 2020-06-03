package net.kitpvp.pluginapi.modules.stats.setting;

import lombok.Getter;
import net.kitpvp.pluginapi.modules.stats.Setting;

import java.util.HashMap;
import java.util.Map;

@Getter
public class NormalSetting implements Setting {

    private static Map<String, Setting> settings = new HashMap<>();

    private final String settingsKey, settingsDefault;
    private final boolean bungee;
    private final boolean local;


    public NormalSetting(String settingsKey, String settingsDefault, boolean bungee, boolean local) {
        this.settingsKey = settingsKey;
        this.settingsDefault = settingsDefault;
        this.bungee = bungee;
        this.local = local;

        settings.put(settingsKey, this);
    }

    public static Setting getSetting(String settingsKey) {
        return settings.get(settingsKey);
    }

    public static <T extends Setting> T getSetting(String settingsKey, Class<T> settingsClass) {
        return settingsClass.cast(getSetting(settingsKey));
    }
}
