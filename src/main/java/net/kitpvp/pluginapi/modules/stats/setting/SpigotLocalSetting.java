package net.kitpvp.pluginapi.modules.stats.setting;

public class SpigotLocalSetting extends ToggleSetting {

    public SpigotLocalSetting(String settingsKey, String settingsDefault, String... values) {
        super(settingsKey, settingsDefault, false, true, values);
    }

}
