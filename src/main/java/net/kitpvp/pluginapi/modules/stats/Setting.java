package net.kitpvp.pluginapi.modules.stats;

import net.kitpvp.pluginapi.modules.stats.setting.NormalSetting;
import net.kitpvp.pluginapi.modules.stats.setting.ToggleSetting;
import net.kitpvp.pluginapi.modules.stats.setting.TrueFalseSetting;

public interface Setting {

    TrueFalseSetting SILENT_ENABLED = new TrueFalseSetting("silentEnabled", "false", true, false);
    TrueFalseSetting STAFF_CHAT = new TrueFalseSetting("staffChat", "false", true, true); // dont sync
    ToggleSetting DAY_NIGHT = new ToggleSetting("dayNight", "default", false, false, "default", "day", "night"); // dont sync
    TrueFalseSetting MESSAGE_ENABLED = new TrueFalseSetting("messageEnabled", "true", true, true); // dont sync
    TrueFalseSetting TAG_ENABLED = new TrueFalseSetting("tagEnabled", "true", true, false); // sync
    TrueFalseSetting TITLE_ENABLED = new TrueFalseSetting("titleEnabled", "true", false, false); // sync
    TrueFalseSetting TITLE_FORCED_OFF = new TrueFalseSetting("titleForcedOff", "false", false, true); // dont sync
    NormalSetting TITLE_SELECTED = new NormalSetting("titleSelected", "", false, false); // sync
    NormalSetting SCOREBOARD_TEAMS_STYLE = new NormalSetting("scoreboardTeams", "default", false, false); // sync
    TrueFalseSetting CLAN_ENABLED = new TrueFalseSetting("clanEnabled", "false", true, false); // sync
    TrueFalseSetting CLAN_CHAT_ENABLED = new TrueFalseSetting("clanChatEnabled", "false", true, true);
    ToggleSetting CHAT_MODE = new ToggleSetting("chatStyle", "global", true, false, "global", "msg");

    TrueFalseSetting STRONG_SPECTATE_MODE = new TrueFalseSetting("strongSpectateMode", "true", true, false);

    String getSettingsKey();

    String getSettingsDefault();

    boolean isLocal();
}
