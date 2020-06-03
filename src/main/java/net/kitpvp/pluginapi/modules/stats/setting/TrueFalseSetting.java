package net.kitpvp.pluginapi.modules.stats.setting;

public class TrueFalseSetting extends ToggleSetting {

    public TrueFalseSetting(String settingsKey, String settingsDefault, boolean bungee, boolean local) {
        super(settingsKey, settingsDefault, bungee, local, "true", "false");
    }
}
